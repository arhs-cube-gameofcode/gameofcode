'use strict';

angular.module('thatsmyspotApp')
	.controller('RouteDeleteController', function($scope, $uibModalInstance, entity, Route) {

        $scope.route = entity;
        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        $scope.confirmDelete = function (id) {
            Route.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };

    });
