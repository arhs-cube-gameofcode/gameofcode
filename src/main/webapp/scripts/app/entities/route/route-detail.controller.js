'use strict';

angular.module('thatsmyspotApp')
    .controller('RouteDetailController', function ($scope, $rootScope, $stateParams, entity, Route) {
        $scope.route = entity;
        $scope.load = function (id) {
            Route.get({id: id}, function(result) {
                $scope.route = result;
            });
        };
        var unsubscribe = $rootScope.$on('thatsmyspotApp:routeUpdate', function(event, result) {
            $scope.route = result;
        });
        $scope.$on('$destroy', unsubscribe);

    });
