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
        // Remove or comment out the following line if products are dynamically generated
        // this.products = this.initializeProducts();
    }

    // Optionally, keep a method to initialize products if needed elsewhere
    // initializeProducts() { ... }
}

module.exports = Store; 