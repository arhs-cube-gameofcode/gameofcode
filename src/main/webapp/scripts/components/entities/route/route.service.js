'use strict';

angular.module('thatsmyspotApp')
    .factory('Route', function ($resource, DateUtils) {
        return $resource('api/routes/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
