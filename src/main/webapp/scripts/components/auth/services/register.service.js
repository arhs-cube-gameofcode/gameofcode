'use strict';

angular.module('thatsmyspotApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


