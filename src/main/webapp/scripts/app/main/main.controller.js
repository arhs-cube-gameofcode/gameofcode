'use strict';

angular.module('thatsmyspotApp')
    .controller('MainController', function ($scope, Principal, $http) {
        Principal.identity().then(function (account) {
            $scope.searchRes = {};
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            // Criteria
            $scope.criteria = {};
            $scope.$watch('criteria.location', function (newValue, oldValue) {
                if (newValue) {
                    $http.get('http://nominatim.openstreetmap.org/search?format=json&limit=20&q=' + newValue).then(function (response) {
                        console.debug(response.data);
                        $scope.searchRes = response.data;

                        if ($scope.searchRes.length == 1) {
                            $scope.criteria.location = $scope.searchRes[0].display_name;
                            $scope.chooseAddr($scope.searchRes[0]);
                        }

                        $scope.searchResDisplay = angular.copy($scope.searchRes);

                        if ($scope.searchRes.length > 10) {
                            $scope.searchResDisplay.length = 10;

                        }
                	});
                } else {
                    $scope.searchRes = [];
                    $scope.searchResDisplay = [];
                }
            });
            $scope.services = [
                {
                    key: "'tourism'='hotel'",
                    label: 'Hotel'
                },
                {
                    key: "'amenity'='restaurant'",
                    label: 'Restaurant'
                }];

            function removeAllMarkers() {

                //var allMarkersObjArray = []; // for marker objects
                //var allMarkersGeoJsonArray = []; // for readable geoJson markers

                $.each(map._layers, function (ml) {

                    //if (map._layers[ml].feature) {
                    //    map.removeLayer(this);
                    //allMarkersObjArray.push(this)
                    //allMarkersGeoJsonArray.push(JSON.stringify(this.toGeoJSON()))
                    //}
                })

            }

            function logArrayElements(element, index, array) {
                console.log('a[' + index + '] = ' + element);
            }


            $scope.criteria.service = $scope.services[0].key;
            $scope.$watch('criteria.service', function (newValue, oldValue) {
                if (newValue) {

                    //map.getLayers().forEach(map.removeLayer());
                    removeAllMarkers();


                    //TODO call the map service changing function

                    //map.removeLayer();
                    //query:"node(area)[highway=bus_stop];node(around:100)[amenity=cinema];out;"

                    new L.OverPassLayer({
                        //node(BBOX)way["+newValue+"];node(BBOX)relation["+newValue+"];
                        query: "node(BBOX)[" + newValue + "];out;",
                    }).addTo(map);

                }
            });

            $scope.getDistanceFromLatLonInKm = function(lat1,lon1,lat2,lon2) {
            	  var R = 6371; 
            	  var dLat = $scope.deg2rad(lat2-lat1);
            	  var dLon = $scope.deg2rad(lon2-lon1); 
            	  var a = 
            	    Math.sin(dLat/2) * Math.sin(dLat/2) +
            	    Math.cos($scope.deg2rad(lat1)) * Math.cos($scope.deg2rad(lat2)) * 
            	    Math.sin(dLon/2) * Math.sin(dLon/2)
            	    ; 
            	  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
            	  var d = R * c; // Distance in km
            	  return d;
            	}

            $scope.deg2rad = function(deg) {
            	  return deg * (Math.PI/180)
            }
            
            $scope.chooseAddr = function(lat, lng){
            	  var location = new L.LatLng(lat, lng);
            	  map.panTo(location);
            	  map.setZoom(25);
            	  var fixedMarker = L.marker(location).bindPopup('Location').addTo(map);
            	  
                  $http.get('/api/routes?lat=' + lat + '&lng=' + lng + '&distance=' + 150).then(function(response) {
                	  console.log(response.data);
                	  
                	  response.data.forEach(function(bussLine){
                		  //TODO when real data, calculate the closest station relative to lat, lng
                		  
                		  bussLine.inDist = false;
                		  
                		  bussLine.stations.forEach(function(station){
                			  var diff = $scope.getDistanceFromLatLonInKm(lat, lng, station.lat, station.lng) * 1000;
                			  //make 300 configurable
                			  if(diff < 200){
                				  console.log(station);
                				  L.marker(new L.LatLng(station.lat, station.lng), {
                				        icon: $scope.north
                			      }).bindPopup('Bus id:' + bussLine.id).addTo(map);
                				  
                				  bussLine.inDist = true;
                			  }
                		  })
                		  
                		  if(bussLine.inDist == true){
                			  var coord = $scope.calculateRoutes(lat, lng, bussLine.stations, 2500);
                			  
                			  var c = 2;
                			  for(c in coord){
                    			  L.marker(new L.LatLng(coord[c].lat, coord[c].lng), {
                				        icon: $scope.northSmall
                			      }).addTo(map);
                			  }
                			  
                			  L.polyline(coord, {color: 'red'}).addTo(map);
                		  }
                	  })
                  });  
            }
            
            $scope.north = L.icon({
                iconUrl: '//esri.github.io/esri-leaflet/img/bus-stop-north.png',
                iconRetinaUrl: '//esri.github.io/esri-leaflet/img/bus-stop-north@2x.png',
                iconSize: [27, 31],
                iconAnchor: [13.5, 17.5],
                popupAnchor: [0, -11],
              })
             
            $scope.northSmall = L.icon({
                iconUrl: '//esri.github.io/esri-leaflet/img/bus-stop-north.png',
                iconRetinaUrl: '//esri.github.io/esri-leaflet/img/bus-stop-north@2x.png',
                iconSize: [14, 16],
                iconAnchor: [13.5, 17.5],
                popupAnchor: [0, -11],
              })              
              
            $scope.calculateRoutes = function (lat, lng, stations, limitDistance){
                var pathCoords = [];
                var dist = 0;
                for(var i in stations) {
                    var x = stations[i].lat;
                    var y = stations[i].lng;
                   
                    dist+=$scope.getDistanceFromLatLonInKm(lat, lng, x, y) * 1000;
                    
                    if(dist < limitDistance){
                    	pathCoords.push(new L.LatLng(x, y));
                        
                        lat = x;
                        lng = y;
                    } else break;
                }
 
                return pathCoords;
            }  
            

            // Criteria distance
            $scope.$watch('criteria.distance', function(newValue, oldValue) {
                if(newValue) {
                    //TODO set distance
                } else {
                    //TODO no distance
                }
            });

            // Map
            var attr_osm = 'Map data &copy; <a href="http://openstreetmap.org/">OpenStreetMap</a> contributors',
                attr_overpass = 'POI via <a href="http://www.overpass-api.de/">Overpass API</a>';
            var osm = new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                opacity: 0.7,
                attribution: [attr_osm, attr_overpass].join(', ')
            });

            var map = new L.Map('mapid').addLayer(osm).setView(new L.LatLng(49.61134, 6.13917), 12);

            //OverPassAPI overlay
            var opl = new L.OverPassLayer({
                //node(BBOX)way['tourism'='hotel'];node(BBOX)relation['tourism'='hotel'];
                query: "node(BBOX)['tourism'='hotel'];out;",
            });
            map.addLayer(opl);
        });

        $scope.findPOINearby = function (lat, lon, maxDistance) {
            // TODO Find list of hotels
            var POInearby = {};
            getPOIList().forEach(function(element, index, array) {
                if (getDistanceFromLatLonInKm(lat, lon, element[0], element[1]) < maxDistance) {
                    POInearby.push([element]);
                }
            });
            return POInearby;
            // TODO check distance
        }

        function getPOIList(){
            return [[49.60596, 6.11064]];
        }

        function getDistanceFromLatLonInKm(lat1, lon1, lat2, lon2) {
            var R = 6371; // Radius of the earth in km
            var dLat = deg2rad(lat2 - lat1);  // deg2rad below
            var dLon = deg2rad(lon2 - lon1);
            var a =
                    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                    Math.sin(dLon / 2) * Math.sin(dLon / 2)
                ;
            var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            var d = R * c; // Distance in km
            return d;
        }

        function deg2rad(deg) {
            return deg * (Math.PI / 180)
        }


    });
