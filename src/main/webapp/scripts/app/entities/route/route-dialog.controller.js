'use strict';

angular.module('thatsmyspotApp').controller('RouteDialogController',
    ['$scope', '$stateParams', '$uibModalInstance', 'entity', 'Route',
        function($scope, $stateParams, $uibModalInstance, entity, Route) {

        $scope.route = entity;
        $scope.load = function(id) {
            Route.get({id : id}, function(result) {
                $scope.route = result;
            });
        };

        var onSaveSuccess = function (result) {
            $scope.$emit('thatsmyspotApp:routeUpdate', result);
            $uibModalInstance.close(result);
            $scope.isSaving = false;
        };

        var onSaveError = function (result) {
            $scope.isSaving = false;
        };

        $scope.save = function () {
            $scope.isSaving = true;
            if ($scope.route.id != null) {
                Route.update($scope.route, onSaveSuccess, onSaveError);
            } else {
                Route.save($scope.route, onSaveSuccess, onSaveError);
            }
        };

        $scope.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
}]);
