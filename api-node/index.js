const express = require('express');
const Product = require('./models/Product');
const Store = require('./models/Store');
const stores = require('./data/stores'); // Import the stores data
const app = express();
const PORT = process.env.PORT || 3000;

// Middleware to parse JSON
app.use(express.json());

// Basic route
app.get('/', (req, res) => {
  res.send('Hello from ShopSmart API!');
});

// GET all products with random prices
app.get('/api/products', (req, res) => {
  const products = [
    new Product('milk'),
    new Product('eggs'),
    new Product('bread'),
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

// Start the server
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});