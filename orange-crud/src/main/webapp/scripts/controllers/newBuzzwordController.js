
angular.module('orangecrud').controller('NewBuzzwordController', function ($scope, $location, locationParser, flash, BuzzwordResource , UserResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.buzzword = $scope.buzzword || {};
    
    $scope.userList = UserResource.queryAll(function(items){
        $scope.userSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("userSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.buzzword.user = {};
            $scope.buzzword.user.id = selection.value;
        }
    });
    
    $scope.includeList = [
        "true",
        "false"
    ];


    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The buzzword was created successfully.'});
            $location.path('/Buzzwords');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        BuzzwordResource.save($scope.buzzword, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Buzzwords");
    };
});