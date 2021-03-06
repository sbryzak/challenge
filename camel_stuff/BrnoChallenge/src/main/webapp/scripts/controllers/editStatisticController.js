

angular.module('orangecrud').controller('EditStatisticController', function($scope, $routeParams, $location, flash, StatisticResource , PollResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.statistic = new StatisticResource(self.original);
            PollResource.queryAll(function(items) {
                $scope.pollSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.statistic.poll && item.id == $scope.statistic.poll.id) {
                        $scope.pollSelection = labelObject;
                        $scope.statistic.poll = wrappedObject;
                        self.original.poll = $scope.statistic.poll;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The statistic could not be found.'});
            $location.path("/Statistics");
        };
        StatisticResource.get({StatisticId:$routeParams.StatisticId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.statistic);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'The statistic was updated successfully.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.statistic.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Statistics");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The statistic was deleted.'});
            $location.path("/Statistics");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        }; 
        $scope.statistic.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("pollSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.statistic.poll = {};
            $scope.statistic.poll.id = selection.value;
        }
    });
    
    $scope.get();
});