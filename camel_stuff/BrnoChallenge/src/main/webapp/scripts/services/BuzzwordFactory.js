angular.module('orangecrud').factory('BuzzwordResource', function($resource){
    var resource = $resource('rest/buzzwords/:BuzzwordId',{BuzzwordId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});