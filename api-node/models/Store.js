const Product = require('./Product');

/**
 * Represents a Grocery Store.
 */
class Store {
    /**
     * Creates a new Store instance.
     * @param {number} id - The unique identifier for the store.
     * @param {string} name - The name of the store.
     * @param {string} address - The address of the store.
     * @param {object} coordinates - The coordinates of the store.
     * @param {number} coordinates.latitude - The latitude of the store.
     * @param {number} coordinates.longitude - The longitude of the store.
     */
    constructor(id, name, address, coordinates) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.coordinates = coordinates;
        this.products = this.initializeProducts();
    }

    /**
     * Initializes the store's products with random stock and price.
     * @returns {object} - An object containing products with their price and stock.
     */
    initializeProducts() {
        const productTypes = ['milk', 'eggs', 'bread'];
        const products = {};

        productTypes.forEach(type => {
            const product = new Product(type);
            const stock = Math.floor(Math.random() * 101); // Random stock between 0-100
            products[type] = {
                price: product.price,
                stock: stock
            };
        });

        return products;
    }
}

module.exports = Store; 