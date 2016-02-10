
angular.module('orangecrud').controller('NewStatisticController', function ($scope, $location, locationParser, flash, StatisticResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.statistic = $scope.statistic || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The statistic was created successfully.'});
            $location.path('/Statistics');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        StatisticResource.save($scope.statistic, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Statistics");
    };
});