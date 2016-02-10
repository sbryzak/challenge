angular.module('orangecrud').factory('URLResource', function($resource){
    var resource = $resource('rest/urls/:URLId',{URLId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});