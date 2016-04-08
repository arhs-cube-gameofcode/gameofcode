 'use strict';

angular.module('thatsmyspotApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-thatsmyspotApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-thatsmyspotApp-params')});
                }
                return response;
            }
        };
    });
