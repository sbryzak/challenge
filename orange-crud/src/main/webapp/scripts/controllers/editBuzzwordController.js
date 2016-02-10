

angular.module('orangecrud').controller('EditBuzzwordController', function($scope, $routeParams, $location, flash, BuzzwordResource , UserResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.buzzword = new BuzzwordResource(self.original);
            UserResource.queryAll(function(items) {
                $scope.userSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.buzzword.user && item.id == $scope.buzzword.user.id) {
                        $scope.userSelection = labelObject;
                        $scope.buzzword.user = wrappedObject;
                        self.original.user = $scope.buzzword.user;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The buzzword could not be found.'});
            $location.path("/Buzzwords");
        };
        BuzzwordResource.get({BuzzwordId:$routeParams.BuzzwordId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.buzzword);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'The buzzword was updated successfully.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.buzzword.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Buzzwords");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The buzzword was deleted.'});
            $location.path("/Buzzwords");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        }; 
        $scope.buzzword.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("userSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.buzzword.user = {};
            $scope.buzzword.user.id = selection.value;
        }
    });
    $scope.includeList = [
        "true",
        "false"
    ];
    
    $scope.get();
});