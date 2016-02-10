

angular.module('orangecrud').controller('EditHistoryController', function($scope, $routeParams, $location, flash, HistoryResource , URLResource, HistoryResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.history = new HistoryResource(self.original);
            URLResource.queryAll(function(items) {
                $scope.urlSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.history.url && item.id == $scope.history.url.id) {
                        $scope.urlSelection = labelObject;
                        $scope.history.url = wrappedObject;
                        self.original.url = $scope.history.url;
                    }
                    return labelObject;
                });
            });
            HistoryResource.queryAll(function(items) {
                $scope.historySelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.history.history && item.id == $scope.history.history.id) {
                        $scope.historySelection = labelObject;
                        $scope.history.history = wrappedObject;
                        self.original.history = $scope.history.history;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The history could not be found.'});
            $location.path("/Histories");
        };
        HistoryResource.get({HistoryId:$routeParams.HistoryId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.history);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'The history was updated successfully.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.history.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Histories");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The history was deleted.'});
            $location.path("/Histories");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        }; 
        $scope.history.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("urlSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.history.url = {};
            $scope.history.url.id = selection.value;
        }
    });
    $scope.$watch("historySelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.history.history = {};
            $scope.history.history.id = selection.value;
        }
    });
    
    $scope.get();
});