'use strict';

angular.module('thatsmyspotApp')
    .controller('RouteController', function ($scope, $state, Route) {

        $scope.routes = [];
        $scope.loadAll = function() {
            Route.query(function(result) {
               $scope.routes = result;
            });
        };
        $scope.loadAll();


        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.route = {
                id: null
            };
        };
    });
