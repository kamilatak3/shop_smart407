const Store = require('../models/Store');

const stores = [
    new Store(
        1,
        "Pick 'n Save",
        "2502 Shopko Dr, Madison, WI 53704",
        { latitude: 43.0515, longitude: -89.3860 }
    ),
    new Store(
        2,
        "Pick 'n Save",
        "3650 University Ave, Madison, WI 53705",
        { latitude: 43.1042, longitude: -89.3975 }
    ),
    new Store(
        3,
        "Walmart",
        "2151 Royal Ave, Monona, WI 53713",
        { latitude: 43.0740, longitude: -89.2723 }
    ),
    new Store(
        4,
        "Walmart",
        "4198 Nakoosa Trail, Madison, WI 53714",
        { latitude: 43.1090, longitude: -89.2965 }
    ),
    new Store(
        5,
        "Festival Foods",
        "810 E Washington Ave, Madison, WI 53703",
        { latitude: 43.0731, longitude: -89.3987 }
    ),
    new Store(
        6,
        "Target",
        "750 Hilldale Wy, Madison, WI 53705",
        { latitude: 43.0825, longitude: -89.3978 }
    ),
    new Store(
        7,
        "Target",
        "610 State St, Madison, WI 53703",
        { latitude: 43.0742, longitude: -89.3901 }
    ),
    new Store(
        8,
        "Target",
        "4301 Lien Rd, Madison, WI 53704",
        { latitude: 43.0518, longitude: -89.3859 }
    )
];

module.exports = stores; 