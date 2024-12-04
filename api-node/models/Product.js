class Product {
    /**
     * Creates a new Product instance.
     * @param {string} type - The type of the product ('milk', 'eggs', or 'bread').
     */
    constructor(type) {
        this.type = type.toLowerCase();
        this.price = this.generateRandomPrice();
    }

    /**
     * Generates a random price based on the product type.
     * @returns {number} - The randomly generated price.
     */
    generateRandomPrice() {
        let min, max;

        switch (this.type) {
            case 'milk':
                min = 2.0;
                max = 3.5;
                break;
            case 'eggs':
                min = 3.0;
                max = 5.0;
                break;
            case 'bread':
                min = 1.0;
                max = 2.0;
                break;
            default:
                throw new Error(`Invalid product type: ${this.type}`);
        }

        // Generate a random price within the specified range, rounded to 2 decimal places
        return parseFloat((Math.random() * (max - min) + min).toFixed(2));
    }
}

module.exports = Product; 