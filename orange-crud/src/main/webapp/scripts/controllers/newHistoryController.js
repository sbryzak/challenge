
angular.module('orangecrud').controller('NewHistoryController', function ($scope, $location, locationParser, flash, HistoryResource , URLResource, HistoryResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.history = $scope.history || {};
    
    $scope.urlList = URLResource.queryAll(function(items){
        $scope.urlSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("urlSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.history.url = {};
            $scope.history.url.id = selection.value;
        }
    });
    
    $scope.historyList = HistoryResource.queryAll(function(items){
        $scope.historySelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("historySelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.history.history = {};
            $scope.history.history.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The history was created successfully.'});
            $location.path('/Histories');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        HistoryResource.save($scope.history, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Histories");
    };
});