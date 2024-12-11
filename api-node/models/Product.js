class Product {
static nextId = 0;
    /**
     * Creates a new Product instance.
     * @param {string} type - The type of the product (e.g., 'milk', 'eggs', 'bread', etc.).
     */
    constructor(type) {
        this.id = Product.nextId++;
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
            // Fruits
            case 'apples':
            case 'bananas':
            case 'oranges':
                min = 0.50;
                max = 3.00;
                break;

            // Dairy
            case 'milk':
            case 'eggs':
            case 'cheese':
            case 'butter':
            case 'yogurt':
                min = 1.00;
                max = 6.00;
                break;

            // Meat
            case 'chicken':
            case 'beef':
                min = 3.00;
                max = 15.00;
                break;

            // Pantry
            case 'bread':
            case 'pasta':
            case 'rice':
            case 'cereal':
            case 'juice':
                min = 1.00;
                max = 10.00;
                break;

            default:
                throw new Error(`Invalid product type: ${this.type}`);
        }

        // Generate a random price within the specified range, rounded to 2 decimal places
        return parseFloat((Math.random() * (max - min) + min).toFixed(2));
    }
}

module.exports = Product;