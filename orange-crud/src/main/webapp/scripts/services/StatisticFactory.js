angular.module('orangecrud').factory('StatisticResource', function($resource){
    var resource = $resource('rest/statistics/:StatisticId',{StatisticId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});