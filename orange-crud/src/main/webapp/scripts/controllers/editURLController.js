

angular.module('orangecrud').controller('EditURLController', function($scope, $routeParams, $location, flash, URLResource , UserResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.uRL = new URLResource(self.original);
            UserResource.queryAll(function(items) {
                $scope.userSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.uRL.user && item.id == $scope.uRL.user.id) {
                        $scope.userSelection = labelObject;
                        $scope.uRL.user = wrappedObject;
                        self.original.user = $scope.uRL.user;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The uRL could not be found.'});
            $location.path("/URLs");
        };
        URLResource.get({URLId:$routeParams.URLId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.uRL);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'The uRL was updated successfully.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.uRL.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/URLs");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The uRL was deleted.'});
            $location.path("/URLs");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        }; 
        $scope.uRL.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("userSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.uRL.user = {};
            $scope.uRL.user.id = selection.value;
        }
    });
    
    $scope.get();
});