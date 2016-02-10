
angular.module('orangecrud').controller('NewURLController', function ($scope, $location, locationParser, flash, URLResource , UserResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.uRL = $scope.uRL || {};
    
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
            $scope.uRL.user = {};
            $scope.uRL.user.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The uRL was created successfully.'});
            $location.path('/URLs');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        URLResource.save($scope.uRL, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/URLs");
    };
});