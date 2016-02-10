'use strict';

angular.module('orangecrud',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Buzzwords',{templateUrl:'views/Buzzword/search.html',controller:'SearchBuzzwordController'})
      .when('/Buzzwords/new',{templateUrl:'views/Buzzword/detail.html',controller:'NewBuzzwordController'})
      .when('/Buzzwords/edit/:BuzzwordId',{templateUrl:'views/Buzzword/detail.html',controller:'EditBuzzwordController'})
      .when('/Statistics',{templateUrl:'views/Statistic/search.html',controller:'SearchStatisticController'})
      .when('/Statistics/new',{templateUrl:'views/Statistic/detail.html',controller:'NewStatisticController'})
      .when('/Statistics/edit/:StatisticId',{templateUrl:'views/Statistic/detail.html',controller:'EditStatisticController'})
      .when('/URLs',{templateUrl:'views/URL/search.html',controller:'SearchURLController'})
      .when('/URLs/new',{templateUrl:'views/URL/detail.html',controller:'NewURLController'})
      .when('/URLs/edit/:URLId',{templateUrl:'views/URL/detail.html',controller:'EditURLController'})
      .when('/Users',{templateUrl:'views/User/search.html',controller:'SearchUserController'})
      .when('/Users/new',{templateUrl:'views/User/detail.html',controller:'NewUserController'})
      .when('/Users/edit/:UserId',{templateUrl:'views/User/detail.html',controller:'EditUserController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
