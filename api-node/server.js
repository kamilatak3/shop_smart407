const express = require('express');
const cors = require('cors');
const Product = require('./models/Product');
const Store = require('./models/Store');
const stores = require('./data/stores'); // Import the stores data

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(cors());
app.use(express.json());

// Utility function to generate random integer
function getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

// Utility function to generate random price
function getRandomPrice(min, max) {
    return parseFloat((Math.random() * (max - min) + min).toFixed(2));
}

// Sample products list (if needed elsewhere)
const sampleProducts = [
    "Apples",
    "Bananas",
    "Oranges",
    "Milk",
    "Bread",
    "Eggs",
    "Cheese",
    "Butter",
    "Chicken",
    "Beef",
    "Pasta",
    "Rice",
    "Cereal",
    "Yogurt",
    "Juice"
];

// Basic route
app.get('/', (req, res) => {
    res.send('Hello from ShopSmart API!');
});

// GET all products with random prices (if needed)
app.get('/api/products', (req, res) => {
    const products = [
        new Product('milk'),
        new Product('eggs'),
        new Product('bread'),
        new Product('apples'),
        new Product('bananas'),
        new Product('oranges'),
        new Product('cheese'),
        new Product('butter'),
        new Product('chicken'),
        new Product('beef'),
        new Product('pasta'),
        new Product('rice'),
        new Product('cereal'),
        new Product('yogurt'),
        new Product('juice')
    ];
    res.json(products);
});

/**
 * GET Store Information Based on Coordinates
 * 
 * Query Parameters:
 * - latitude: number
 * - longitude: number
 * 
 * Example: /api/store?latitude=43.0740&longitude=-89.2723
 */
app.get('/api/store', (req, res) => {
    const { latitude, longitude } = req.query;

    if (!latitude || !longitude) {
        return res.status(400).json({ error: 'Latitude and longitude are required.' });
    }

    // Find the store matching the provided coordinates
    const store = stores.find(s => 
        s.coordinates.latitude === parseFloat(latitude) && 
        s.coordinates.longitude === parseFloat(longitude)
    );

    if (!store) {
        return res.status(404).json({ error: 'Store not found with the provided coordinates.' });
    }

    res.json({
        name: store.name,
        address: store.address,
        coordinates: store.coordinates,
        products: store.products
    });
});

/**
 * GET Products for a Specific Shop by shopId
 * 
 * Route Parameters:
 * - shopId: number
 * 
 * Example: /api/shop/1/products
 */
app.get('/api/shop/:shopId/products', (req, res) => {
    const shopId = parseInt(req.params.shopId, 10);

    // Find the store by ID
    const store = stores.find(s => s.id === shopId);

    if (!store) {
        return res.status(404).json({ error: 'Store not found.' });
    }

    // Define the list of product types
    const productTypes = [
        "Apples",
        "Bananas",
        "Oranges",
        "Milk",
        "Bread",
        "Eggs",
        "Cheese",
        "Butter",
        "Chicken",
        "Beef",
        "Pasta",
        "Rice",
        "Cereal",
        "Yogurt",
        "Juice"
    ];

    // Generate random quantities and prices for each product type
    const products = productTypes.map((type, index) => {
        const product = new Product(type); // Generates a random price based on type
        const stock = getRandomInt(0, 100); // Random stock between 0 and 100
        return {
            id: index + 1, // Assign unique integer IDs starting from 1
            name: type,
            quantity: stock,
            price: product.price,
            imageName: type.toLowerCase() // Assuming image names match product types
        };
    });

    res.json(products);
});

// Start the server
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
