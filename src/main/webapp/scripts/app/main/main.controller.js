'use strict';

angular.module('thatsmyspotApp')
    .controller('MainController', function ($scope, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            // Criteria
            $scope.criteria = {};
            $scope.$watch('criteria.location', function(newValue, oldValue) {
                if(newValue) {
                    //TODO call the map centering function.
                }
            });
            $scope.services = [{
                key: 'tourism:hotel',
                label: 'Hotel'
            }];
            $scope.criteria.service = $scope.services[0].key;
            $scope.$watch('criteria.service', function(newValue, oldValue) {
                if(newValue) {
                    //TODO call the map service changing function
                }
            });

            // Map
            var attr_osm = 'Map data &copy; <a href="http://openstreetmap.org/">OpenStreetMap</a> contributors',
                attr_overpass = 'POI via <a href="http://www.overpass-api.de/">Overpass API</a>';
            var osm = new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {opacity: 0.7, attribution: [attr_osm, attr_overpass].join(', ')});

            var map = new L.Map('mapid').addLayer(osm).setView(new L.LatLng(49.61134, 6.13917), 12);

        //OverPassAPI overlay
            var opl = new L.OverPassLayer({
                query: "node(BBOX)['tourism'='hotel'];out;",
            });

            map.addLayer(opl);

        });
    });
