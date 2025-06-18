INSERT INTO categories (category_name, photo, description) VALUES
('History', 'https://www.ansa.it/webimages/img_900x700/2015/1/21/f4973dc084570afc23175b7c9a1daf63.jpg', 'Discover historical artifacts and ancient relics that tell the past.'),
('Art', 'https://images.joseartgallery.com/100736/what-kind-of-art-is-popular-right-now.jpg', 'Explore unique works of art, from classics to modern masterpieces.'),
('Automotive', 'https://www.motorcarclassics.com/galleria_images/302/302_p8_l.jpg', 'Collect iconic cars and motorcycles for true motor enthusiasts.'),
('Trading Cards', 'https://www.creativefabrica.com/wp-content/uploads/2017/12/Retro-Trading-Card-Template-by-JumboDesign-1.jpg', 'Rare and vintage trading cards, from Pokémon to baseball.'),
('Interior Design', 'https://png.pngtree.com/background/20230527/original/pngtree-home-interior-design-with-black-walls-and-wooden-furniture-picture-image_2758154.jpg', 'Interior design and elegant furniture for every home style.'),
('Home Accessories', 'https://gembah.com/wp-content/uploads/2022/07/home-kitchen-gadgets-scaled-1.jpeg', 'Creative and functional accessories to improve your home.'),
('Comics', 'https://www.momarte.com/blog/wp-content/uploads/come-realizzare-un-fumetto_00.jpg', 'Collectible comics, graphic novels, and illustrated stories.'),
('Toys & Action Figures', 'https://farm5.static.flickr.com/4091/5069464333_e84224ae43_z.jpg', 'Vintage toys and collectible action figures.'),
('Jewelry', 'https://img.freepik.com/premium-photo/elegant-jewelry-set-jewellery-set-with-gemstones_955834-13008.jpg', 'Refined and precious jewelry for every occasion.'),
('Books & Literature', 'https://www.liveabout.com/thmb/YnUt_D5XoCzqGjkSBbuActS7D0s=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/books-865109088-5add5a4f303713003710857b.jpg', 'Rare books, special editions, and unmissable literary works.'),
('Apparel & Fashion', 'https://wallpaperaccess.com/full/1272031.jpg', 'Fashion and clothing, from vintage trends to contemporary styles.'),
('Coins', 'https://lp.donnad.it/sites/default/files/styles/r_visual_d/public/201710/monete-antiche-come-pulire-senza-rovinare.jpg?itok=hyLdjXY4', 'Historical and collectible coins for expert numismatists.'),
('Music Memorabilia', 'https://images.wired.it/wp-content/uploads/2014/11/1417081664_vinyl_image.jpg', 'Musical memories: vinyls, posters, and legendary items.'),
('Movies & Cinema', 'https://www.adorama.com/alc/wp-content/uploads/2018/03/shutterstock_226081837.jpg', 'Movie collectibles: posters, gadgets, and cinema relics.'),
('Photography', 'https://wallpaperaccess.com/full/4413285.jpg', 'Cameras, lenses, and gear for passionate photographers.'),
('Watches', 'https://www.kalory.co.uk/wp-content/uploads/2017/03/concept-and-still-life-picture-for-watch-brand.jpg', 'Wrist and collectible watches, combining elegance and precision.');

INSERT INTO users (name, lastname, password, email, country) VALUES
('Alice', 'Rossi', 'hashed_password_1', 'alice.rossi@example.com', 'Italy'),
('Luca', 'Bianchi', 'hashed_password_2', 'luca.bianchi@example.com', 'Italy'),
('Emma', 'Verdi', 'hashed_password_3', 'emma.verdi@example.com', 'Italy'),
('Marco', 'Russo', 'hashed_password_4', 'marco.russo@example.com', 'Italy'),
('Giulia', 'Ferrari', 'hashed_password_5', 'giulia.ferrari@example.com', 'Italy'),
('Leonardo', 'Romano', 'hashed_password_6', 'leonardo.romano@example.com', 'Italy'),
('Chiara', 'Gallo', 'hashed_password_7', 'chiara.gallo@example.com', 'Italy'),
('Matteo', 'Greco', 'hashed_password_8', 'matteo.greco@example.com', 'Italy'),
('Francesca', 'Marino', 'hashed_password_9', 'francesca.marino@example.com', 'Italy'),
('Davide', 'Giordano', 'hashed_password_10', 'davide.giordano@example.com', 'Italy');

INSERT INTO user_authorities (user_id, authority) VALUES
(1, 'ROLE_CLIENT'), (2, 'ROLE_CLIENT'), (3, 'ROLE_CLIENT'), (4, 'ROLE_CLIENT'), (5, 'ROLE_CLIENT'),
(6, 'ROLE_CLIENT'), (7, 'ROLE_CLIENT'), (8, 'ROLE_CLIENT'), (9, 'ROLE_CLIENT'), (10, 'ROLE_CLIENT');

INSERT INTO shipping_addresses (user_id, address, city, country, postalcode) VALUES
(1, 'Via Roma 1', 'Turin', 'Italy', '10100'),
(2, 'Via Milano 12', 'Milan', 'Italy', '20100'),
(3, 'Via Napoli 5', 'Naples', 'Italy', '80100'),
(4, 'Viale Venezia 8', 'Venice', 'Italy', '30100'),
(5, 'Piazza Firenze 22', 'Florence', 'Italy', '50100'),
(6, 'Corso Palermo 3', 'Turin', 'Italy', '10137'),
(7, 'Via Cavour 4', 'Genoa', 'Italy', '16100'),
(8, 'Via Dante 18', 'Bologna', 'Italy', '40100'),
(9, 'Via Trieste 7', 'Trieste', 'Italy', '34100'),
(10, 'Piazza Garibaldi 10', 'Rome', 'Italy', '00100');

-- Assuming category_ids from 1 to 16 exist.
INSERT INTO user_preferred_categories (user_id, category_id) VALUES
(1, 2), (1, 10),
(2, 1), (2, 4),
(3, 3), (3, 5),
(4, 2), (4, 7),
(5, 9), (5, 11),
(6, 6), (6, 8),
(7, 12),
(8, 13), (8, 14),
(9, 15),
(10, 16);

INSERT INTO collections (collection_name, completed, category_id, user_id, visibility, description, collection_date, for_sale, sale_price, visibility_status) VALUES
('Venezian Artifacts', TRUE, 1, 1, 'visible', 'Ancient Venetian historical pieces.', '2022-05-10', FALSE, NULL, 'visible'),
('Florentine Renaissance', TRUE, 2, 1, 'visible', 'Renaissance art from Florence.', '2023-03-15', TRUE, 1500.00, 'visible'),
('Classic Cars', FALSE, 3, 2, 'visible', 'Models of classic Italian cars.', '2024-01-10', FALSE, NULL, 'visible'),
('Trading Card Vault', TRUE, 4, 2, 'visible', 'Rare trading cards collected over time.', '2021-09-30', TRUE, 300.00, 'visible'),
('Design Trends', FALSE, 5, 3, 'visible', 'Interior design pieces from different eras.', '2022-11-22', FALSE, NULL, 'visible'),
('Gadget Wonders', TRUE, 6, 3, 'visible', 'Functional accessories from around the world.', '2023-06-01', TRUE, 500.00, 'visible'),
('Superhero Sagas', TRUE, 7, 4, 'visible', 'Comics from Marvel to indie.', '2021-04-12', FALSE, NULL, 'visible'),
('Action Legends', FALSE, 8, 4, 'visible', 'Rare collectible action figures.', '2024-02-05', TRUE, 250.00, 'visible'),
('Italian Jewelry', TRUE, 9, 5, 'visible', 'Handcrafted Italian pieces.', '2023-07-07', FALSE, NULL, 'visible'),
('Fashion Forward', FALSE, 11, 5, 'visible', 'Trend-setting apparel styles.', '2022-08-19', TRUE, 800.00, 'visible'),
('Home Treasures', TRUE, 6, 6, 'visible', 'Beautiful decor accessories.', '2023-10-05', FALSE, NULL, 'visible'),
('Vintage Toys', TRUE, 8, 6, 'visible', 'Toys from the 80s and 90s.', '2021-12-25', TRUE, 220.00, 'visible'),
('Roman Coins', TRUE, 12, 7, 'visible', 'Coins from ancient Rome.', '2022-03-18', FALSE, NULL, 'visible'),
('Musical Legends', TRUE, 13, 8, 'visible', 'Vinyls and posters from famous bands.', '2023-11-11', TRUE, 350.00, 'visible'),
('Cinema Magic', TRUE, 14, 8, 'visible', 'Collectibles from classic movies.', '2021-10-31', TRUE, 900.00, 'visible'),
('Snapshots of Life', FALSE, 15, 9, 'visible', 'Vintage and modern cameras.', '2023-01-15', FALSE, NULL, 'visible'),
('Timepieces Elite', TRUE, 16, 10, 'visible', 'Classic collectible wristwatches.', '2022-09-02', TRUE, 1800.00, 'visible'),
-- More can follow in the next batch…
;

-- Items for collection_id 1: Venezian Artifacts
INSERT INTO items (collection_id, user_id, item_name, item_description, condition, purchase_date, release_date, purchase_price, sale_price, item_version, item_edition, for_sale, visibility_status)
VALUES
(1, 1, 'Murano Glass Vase', 'Hand-blown glass from Murano, 18th century.', 'Good', '2021-06-15', '1750-01-01', 250.00, NULL, 'Original', 'First', FALSE, 'visible'),
(1, 1, 'Gondola Figurine', 'Miniature wooden gondola with inlay details.', 'Fair', '2020-03-10', '1800-01-01', 95.00, NULL, 'Vintage', 'N/A', FALSE, 'visible'),
(1, 1, 'Venetian Coin', 'Republic-era coin with lion insignia.', 'Very Good', '2022-09-20', '1680-01-01', 320.00, NULL, 'Series A', 'Minted 2', FALSE, 'visible'),
(1, 1, 'Ancient Map of Venice', 'Original map detailing 17th-century districts.', 'Good', '2022-01-05', '1650-01-01', 400.00, NULL, 'Manuscript', 'Map Ed.1', FALSE, 'visible'),
(1, 1, 'Trade Ledger', 'Historic ledger from a Venetian merchant.', 'Poor', '2020-11-11', '1705-01-01', 150.00, NULL, 'Vol. 1', 'Damaged Copy', FALSE, 'visible'),
(1, 1, 'Carnival Mask', 'Genuine leather & papier-mâché mask from 19th century.', 'Good', '2021-10-31', '1880-01-01', 110.00, NULL, 'Festival', 'Collector\'s', FALSE, 'visible'),
(1, 1, 'Venetian Scepter', 'Symbolic item believed to be ceremonial.', 'Fair', '2021-07-19', '1690-01-01', 375.00, NULL, 'Ceremony A', 'Limited', FALSE, 'visible'),
(1, 1, 'Maritime Compass', 'Antique brass compass used for naval trade.', 'Very Good', '2023-03-22', '1720-01-01', 290.00, NULL, 'Nautical Set', 'Calibrated', FALSE, 'visible'),
(1, 1, 'Trade Seal Stamp', 'Merchant wax seal with crest.', 'Good', '2022-08-17', '1715-01-01', 130.00, NULL, 'Type 4', 'Bronze Ed.', FALSE, 'visible'),
(1, 1, 'Scroll Fragment', 'Parchment piece from Venetian archive.', 'Poor', '2020-04-23', '1600-01-01', 65.00, NULL, 'Archive', 'Damaged', FALSE, 'visible');

-- Items for collection_id 2: Florentine Renaissance
INSERT INTO items (collection_id, user_id, item_name, item_description, condition, purchase_date, release_date, purchase_price, sale_price, item_version, item_edition, for_sale, visibility_status)
VALUES
(2, 1, 'Botticelli Sketch Repro', 'Rare copy of Botticelli’s preparatory drawings.', 'Good', '2022-02-14', '1483-01-01', 500.00, 700.00, 'Replica A', 'Restored', TRUE, 'visible'),
(2, 1, 'Gold Leaf Frame', 'Ornate renaissance-style gilded frame.', 'Very Good', '2021-07-06', '1500-01-01', 220.00, NULL, 'Frame 6B', 'Florentine', FALSE, 'visible'),
(2, 1, 'Florence Coin Replica', 'Coin used during the Medici era.', 'Fair', '2020-08-30', '1475-01-01', 180.00, 200.00, 'Medici Mint', 'Recast Ed.', TRUE, 'visible'),
(2, 1, 'Marble Bust Fragment', 'Damaged sculpture part from art school archive.', 'Poor', '2019-04-12', '1490-01-01', 90.00, NULL, 'Fragment Z', 'Historic', FALSE, 'visible'),
(2, 1, 'Ink Sketchbook', 'Renaissance-style book with practice drawings.', 'Good', '2022-10-03', '1510-01-01', 300.00, NULL, 'Vol. 2', 'Restored', FALSE, 'visible'),
(2, 1, 'Sculpting Tool Set', 'Vintage chisels used in fine arts workshop.', 'Good', '2021-09-18', '1505-01-01', 120.00, NULL, 'Carver\'s Kit', 'Iron', FALSE, 'visible'),
(2, 1, 'Oil Paint Vial', 'Pigment sample based on 16th century formula.', 'Very Good', '2023-01-11', '1550-01-01', 75.00, NULL, 'Pigment V2', 'Replica', FALSE, 'visible'),
(2, 1, 'Florentine Easel', 'Classic wooden easel restored to working condition.', 'Very Good', '2020-02-28', '1500-01-01', 250.00, 400.00, 'Crafted', 'Heirloom', TRUE, 'visible'),
(2, 1, 'Miniature Mosaic Tile', 'Colorful tile piece from restored mural.', 'Good', '2021-05-16', '1520-01-01', 95.00, NULL, 'Tile No.9', 'Partial Set', FALSE, 'visible'),
(2, 1, 'Artisan Palette', 'Wooden palette used by a modern renaissance artist.', 'Good', '2023-03-19', '2020-01-01', 60.00, NULL, 'Palette 1', 'Collector\'s', FALSE, 'visible');

-- Items for collection_id 3: Classic Cars (user 2)
INSERT INTO items (collection_id, user_id, item_name, item_description, condition, purchase_date, release_date, purchase_price, sale_price, item_version, item_edition, for_sale, visibility_status)
VALUES
(3, 2, '1965 Fiat 500 Model', 'Scale model with authentic decals.', 'Very Good', '2022-07-12', '1965-01-01', 80.00, NULL, '1:24', 'Collector', FALSE, 'visible'),
(3, 2, 'Lancia Delta Poster', 'Limited-edition rally print.', 'Good', '2021-05-23', '1988-01-01', 35.00, NULL, 'Deluxe', 'Signed', FALSE, 'visible'),
(3, 2, 'Vintage License Plate', 'Original Italian plate from 70s.', 'Fair', '2023-03-15', '1972-01-01', 60.00, NULL, 'Metal', 'Scratched', FALSE, 'visible'),
(3, 2, 'Alfa Romeo Keychain', 'Leather keychain from a dealership.', 'Good', '2022-11-10', '1990-01-01', 25.00, NULL, 'Promo', 'Rare', FALSE, 'visible');

-- Items for collection_id 4: Trading Card Vault (user 2)
INSERT INTO items (collection_id, user_id, item_name, item_description, condition, purchase_date, release_date, purchase_price, sale_price, item_version, item_edition, for_sale, visibility_status)
VALUES
(4, 2, 'Pokémon Charizard 1st Ed.', 'Holo foil 1st edition card.', 'Very Good', '2021-08-01', '1999-01-01', 450.00, 800.00, 'Holo', '1st Ed.', TRUE, 'visible'),
(4, 2, 'Yu-Gi-Oh! Blue-Eyes', 'Rare Blue-Eyes White Dragon card.', 'Good', '2020-06-19', '2002-01-01', 250.00, NULL, 'Legendary', '1st Series', FALSE, 'visible'),
(4, 2, 'Basketball Rookie Card', 'Unsigned vintage card from 1984.', 'Fair', '2019-03-10', '1984-01-01', 100.00, NULL, 'Rookie', '2nd Print', FALSE, 'visible');

INSERT INTO user_likes (user_id, item_id) VALUES
(1, 3), (1, 12),
(2, 4), (2, 20),
(3, 7), (3, 23),
(4, 15), (4, 26),
(5, 9), (5, 30);

INSERT INTO user_comments (user_id, item_id, comment) VALUES
(1, 1, 'This vase is a masterpiece!'),
(2, 5, 'Amazing detail on this model.'),
(3, 14, 'Love the colors in this mosaic piece!'),
(4, 8, 'The seal stamp is really unique.'),
(5, 11, 'Such a beautiful example of Renaissance art!');

-- Orders (1 per user for simplicity here)
INSERT INTO orders (buyer_id, shipping_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Order items for each order (1–2 items per order)
INSERT INTO order_items (order_id, seller_id, item_id, price, status) VALUES
(1, 2, 4, 450.00, 'accepted'),
(2, 1, 1, 250.00, 'pending'),
(2, 1, 2, 95.00, 'accepted'),
(3, 4, 15, 80.00, 'accepted'),
(4, 3, 8, 375.00, 'rejected'),
(5, 1, 12, 180.00, 'accepted');
