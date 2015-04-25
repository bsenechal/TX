'use strict';

angular.module('mean.mean-admin').controller('AdminDealsController', ['$scope', 'Global', 'Menus', '$rootScope', '$http', 'Deals',
    function($scope, Global, Menus, $rootScope, $http, Deals) {
        $scope.global = Global;
        $scope.dealSchema = [{
            title: 'title',
            schemaKey: 'title',
            type: 'text',
            inTable: true
        }, {
            title: 'initialPrice',
            schemaKey: 'initialPrice',
            type: 'number',
            inTable: true
        }, {
            title: 'salePrice',
            schemaKey: 'salePrice',
            type: 'number',
            inTable: true
        }, {
            title: 'description',
            schemaKey: 'description',
            type: 'text',
            inTable: true
        }];
        $scope.deal = {};

        $scope.init = function() {
            Deals.query({}, function(deals) {
                $scope.deals = deals;
            });
        };

        $scope.add = function() {
            if (!$scope.deals) $scope.deals = [];

            var deal = new Deals({
                user: $scope.deal.user,
                title: $scope.deal.title,
                initialPrice: $scope.deal.initialPrice,
                salePrice: $scope.deal.salePrice,
                description: $scope.deal.description
            });

            deal.$save(function(response) {
                $scope.deals.push(response);
            });

            this.user = this.title = this.initialPrice = this.salePrice = this.description = '';
        };

        $scope.remove = function(deal) {
            for (var i in $scope.deals) {
                if ($scope.deals[i] === deal) {
                    $scope.deals.splice(i, 1);
                }
            }

            deal.$remove();
        };

        $scope.update = function(deal) {
                deal.$update();
        };
    }
]);
