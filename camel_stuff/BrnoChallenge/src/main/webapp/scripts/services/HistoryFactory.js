angular.module('orangecrud').factory('HistoryResource', function($resource){
    var resource = $resource('rest/histories/:HistoryId',{HistoryId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});