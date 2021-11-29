package com.unistore.server.database;


import com.unistore.server.models.Product;
import com.unistore.server.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
        // logger
        private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return  new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if(productRepository.findAll().size() == 0) {
                    Product product1 = new Product("Laptop Acer Nitro 5 Gaming AN515", "Acer", 1322.37, "https://i.ibb.co/3F9wWqv/image.png", "Acer Nitro 5 Gaming AN515 57 727J i7 (NH.QD9SV.005.) sở hữu vẻ ngoài cá tính, nổi bật và được tích hợp bộ vi xử lý Intel thế hệ 11 tân tiến, card đồ hoạ rời NVIDIA GeForce RTX, hứa hẹn mang đến các trải nghiệm tuyệt vời cho người dùng.",
                            "Windows 10 Home SL", "i7, 11800H, 2.30GHz", "NVIDIA GeForce RTX3050Ti, 4 GB", "8GB, DDR4 2 khe", "512 GB SSD NVMe PCIe", "Bluetooth 5.1, Wi-Fi 6 (802.11ax)", "4-cell Li-ion, 57 Wh", 10);
                    Product product2 = new Product("Laptop MSI Katana Gaming GF66", "MSI", 1282.68, "https://i.ibb.co/qkvQFWd/image.png", "Laptop MSI Katana GF66 11UC i7 (224VN) mang vẻ ngoài cơ động cùng cấu hình mạnh mẽ, đẩy nhanh quá trình xử lý mọi tác vụ hay sẵn sàng đồng hành cùng bạn chiến mọi thể loại game đầy kịch tính.",
                            "Windows 10 Home SL", "i7, 11800H, 2.30 GHz", "NVIDIA GeForce RTX3050, 4 GB", "8GB, DDR4 2 khe", "SSD 512 GB NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "3-cell Li-ion, 53 Wh", 10);
                    Product product3 = new Product("Laptop MSI Katana GF76", "MSI", 1278.27, "https://i.ibb.co/CvZ5gm9/image.png", "Khơi nguồn mọi cảm hứng game thủ với cấu hình mạnh mẽ đến từ con chip Intel thế hệ 11 tân tiến cùng lối thiết kế cơ động, chuẩn gaming, MSI Katana GF76 11UC i7 (441VN) hứa hẹn sẽ là một chiến binh dũng mãnh cùng bạn xông pha trên mọi thế trận.",
                            "Windows 10 Home SL", "i7, 11800H, 2.30GHz", "NVIDIA GeForce RTX3050Ti, 4 GB", "8GB, DDR4 2 khe", "512 GB SSD NVMe PCIe", "Bluetooth 5.1, Wi-Fi 6 (802.11ax)", "4-cell Li-ion, 57 Wh", 10);
                    Product product4 = new Product("Laptop Asus TUF Gaming FX506HCB", "Asus", 1101.90, "https://i.ibb.co/hZP2M6z/image.png", "Laptop Asus TUF Gaming FX506HCB i5 (HN1138W) không chỉ mang trong mình bộ CPU Intel Core i5 thế hệ 11 mạnh mẽ mà còn sở hữu ngoại hình ấn tượng, cho bạn thỏa sức sáng tạo đồ họa cũng như chiến game cực đã.",
                            "Windows 10 Home SL", "Intel Core i5 Tiger Lake - 11400H", "NVIDIA GeForce RTX3050, 4 GB", "8GB, DDR4 2 khe", "SSD 512 GB NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "3-cell Li-ion, 48 Wh", 10);
                    Product product5 = new Product("Laptop Dell Gaming G15 5515 R5", "DELL", 1101.90, "https://i.ibb.co/PwP3L35/image.png", "Laptop Dell Gaming G15 5515 R5 5600H (P105F004CGR) sở hữu thiết kế tinh tế với sắc xám thời thượng cùng cấu hình được thiết lập đầy mạnh mẽ, luôn trong trạng thái sẵn sàng cùng bạn giải quyết mọi việc.",
                            "Windows 11 Home SL", "AMD Ryzen 5 -5600H", "NVIDIA GeForce RTX3050, 4 GB", "8GB, DDR4 2 khe", "SSD 512 GB NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "3-cell Li-ion, 48 Wh", 10);
                    Product product6 = new Product("Laptop Asus TUF Gaming FX516PE", "ASUS", 1278.27, "https://i.ibb.co/8MM0S1G/image.png", "Asus TUF Gaming FX516PE i7 (HN005T) là chiếc laptop gaming mạnh mẽ sở hữu vi xử lý đa nhân Intel thế hệ 11, card đồ họa rời biến các tựa game bom tấn chỉ còn nằm trong tầm tay của bạn cùng với mặt lưng bằng kim loại cứng cáp xứng tầm game thủ hiện đại.",
                            "Windows 10 Home SL", "Intel Core i7 Tiger Lake - 11370H", "NVIDIA GeForce RTX3050Ti, 4 GB", "8GB, DDR4 2 khe", "SSD 512 GB NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "4-cell Li-ion, 76 Wh", 10);
                    Product product7 = new Product("Laptop Acer Predator Triton 300 PT315 53 71DJ", "Acer", 2071.96, "https://i.ibb.co/VLQp6HZ/image.png", "Trải nghiệm chơi game cực mượt mà trên chiếc laptop Acer Predator Triton 300 PT315 53 71DJ i7 (NH.QDSSV.001) mang trong mình bộ xử lý Intel thế hệ 11 mạnh mẽ cân được nhiều tựa game hot.",
                            "Windows 10 Home SL", "Intel Core i7 Tiger Lake - 11800H", "NVIDIA GeForce RTX 3070, 8 GB", "8GB, DDR4 2 khe", "SSD 512 GB NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "4-cell Li-ion, 59 Wh", 10);
                    Product product8 = new Product("Laptop Dell Gaming G3 15", "DELL", 1410.55, "https://i.ibb.co/H7sRJjY/image.png", "Laptop Dell G3 15 i7 (P89F002BWH) thuộc dòng laptop gaming với cấu hình mạnh mẽ, thiết kế trang nhã và rất sang trọng, dễ lựa chọn cho cả người đi đọc, đi làm, là 1 phiên bản tốt để lựa chọn cho cả nhu cầu làm việc, học tập và chơi game giải trí.",
                            "Windows 10 Home SL", "Intel Core i7 Comet Lake-10750H", "NVIDIA GeForce GTX 1660Ti, 6 GB", "16 GB, DDR4 2 khe", "SSD 512 GB NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "4-cell Li-ion, 59 Wh", 10);
                    Product product9 = new Product("Laptop MSI Modern 14 B11MOU", "MSI", 921.12, "https://i.ibb.co/fXgmcQb/image.png", "MSI Modern 14 B11MOU i7 (847VN) là một chiếc laptop học tập - văn phòng ở mức giá tầm trung nhưng sở hữu sức mạnh hiệu năng vượt trội đến từ con chip Intel thế hệ 11 hiện đại cùng vẻ ngoài sang trọng, cao cấp.",
                            "Windows 10 Home SL", "Intel Core i7 Tiger Lake- 11370H", "Intel Iris Xe Graphics", "8 GB, DDR4 2 khe", "SSD 512 GB NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "4-cell Li-ion, 76 Wh", 10);
                    Product product10 = new Product("Laptop Dell Vostro 3400", "DELL", 791.04, "https://i.ibb.co/pLM4PmC/image.png", "Với hiệu năng vượt trội đến từ con chip Intel Gen 11 tân tiến ẩn bên trong vẻ ngoài mang phong cách tối giản, thanh lịch, laptop Dell Vostro 3400 i5 (70253900) sẽ là một trong những gợi ý đáng để bạn tìm hiểu và chọn mua.",
                            "Windows 10 Home SL", "Intel Core i5 Tiger Lake-1135G7", "Intel Iris Xe Graphics", "8 GB, DDR4 2 khe", "SSD 512 GB NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "3-cell Li-ion, 42 Wh", 10);
                    Product product11 = new Product("Laptop MSI Modern 15 A11MU", "MSI", 795.45, "https://i.ibb.co/sPJN7wS/image.png", "Modern 15 A11MU (680VN) là phiên bản laptop học tập - văn phòng đến từ nhà MSI với xu hướng thiết kế gọn nhẹ, hiện đại cùng hiệu năng vượt trội đến từ con chip Intel Gen 11 ấn tượng, đáp ứng tối đa nhu cầu giải trí và làm việc cho người dùng.",
                            "Windows 10 Home SL", "Intel Core i5 Tiger Lake- 1155G7", "Intel Iris Xe Graphics", "8 GB, DDR4 2 khe", "SSD 512 GB NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "3-cell, 52 Wh", 10);
                    Product product12 = new Product("Laptop Acer Aspire A315 56 308N", "Acer", 497.82, "https://i.ibb.co/PM86srT/image.png", "Laptop Acer Aspire A315 56 308N i3 (NX.HS5SV.00C) là mẫu laptop học tập văn phòng thuộc phân khúc giá thấp. Máy trang bị vi xử lý thế hệ mới của Intel, cho hiệu năng đủ dùng đối với các nhu cầu cơ bản, phù hợp với học sinh và sinh viên.",
                            "Windows 10 Home SL", "Intel Core i3 Ice Lake-1005G1", "Intel UHD Graphics", "4 GB, DDR4 2 khe", "SSD 256 GB NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "2-cell Li-ion, 36.7 Wh", 10);
                    Product product13 = new Product("Laptop Asus ZenBook UX325EA", "ASUS", 995.19, "https://i.ibb.co/8d21hXD/image.png", "Chắc chắn hơn bao giờ hết nhờ độ bền chuẩn quân đội, laptop Asus Zenbook UX325EA (KG363T) sở hữu kiểu dáng tinh tế cùng hiệu năng tối ưu nhờ CPU Intel thế hệ 11, giúp bạn xử lý nhanh gọn và chính xác mọi tác vụ.",
                            "Windows 10 Home SL", "Intel Core i5 Tiger Lake-1135G7", "Intel Iris Xe Graphics", "8 GB, LPDDR4X (On board)", "512 GB SSD NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "4-cell Li-ion, 67 Wh", 10);
                    Product product14 = new Product("Laptop Dell Latitude 3520", "DELL", 1298.12, "https://i.ibb.co/Tt1NM9j/image.png", "Laptop Dell Latitude 3520 i7 (70261780) sở hữu thiết hiện đại thường thấy của các sản phẩm nhà Dell, nhưng mang trong mình cấu hình mạnh mẽ vượt trội, là người trợ thủ đắc lực cho bạn từ công việc đến giải trí.",
                            "Windows 10 Pro", "Intel Core i7 Tiger Lake-1165G7", "Intel Iris Xe Graphics", "8 GB, DDR4 2 khe", "512 GB SSD NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "3-cell Li-ion, 41 Wh", 10);
                    Product product15 = new Product("Laptop Asus VivoBook Pro 15 OLED K3500PC", "ASUS", 1175.54, "https://i.ibb.co/nsj0pJP/image.png", "Sự cải tiến của phiên bản laptop Asus VivoBook Pro 15 OLED K3500PC i5 (L1045T) mang đến cho bạn tầm nhìn trọn vẹn với hiệu năng mạnh mẽ, cùng thiết kế thời thượng, bên bạn trên mọi nẻo đường, nâng cao năng suất công việc.",
                            "Windows 10 Home SL", "Intel Core i5 Tiger Lake - 11300H", "NVIDIA GeForce RTX3050, 4 GB", "16 GB, DDR4 (On board)", "512 GB SSD NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "3-cell Li-ion, 63 Wh", 10);
                    Product product16 = new Product("Laptop Dell Inspiron 14 5410", "DELL", 1142.91, "https://i.ibb.co/8Ms4z9S/image.png", "Dell Inspiron 14 5410 i5 (N4I5547W) mang phong cách thiết kế tối giản tinh tế cùng sở hữu con chip Intel thế hệ 11 tiên tiến, card đồ hoạ rời, đảm bảo đáp ứng mượt mà các tác vụ, nhu cầu thiết yếu của học sinh - sinh viên.",
                            "Windows 10 Home SL", "Intel Core i5 Tiger Lake - 1155G7", "NVIDIA GeForce MX350, 2 GB", "8 GB, DDR4 2 khe", "512 GB SSD NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "3-cell Li-ion, 41 Wh", 10);
                    Product product17 = new Product("Laptop Asus VivoBook Pro 15 OLED M3500QC", "ASUS", 1113.8, "https://i.ibb.co/SswyJ5G/image.png", "Đại diện cho thế hệ sản phẩm tân tiến, Asus VivoBook Pro 15 OLED M3500QC R5 5600H (L1105T) không những được thiết kế thời thượng, mà còn sở hữu cấu hình đáng kinh ngạc, hứa hẹn sẽ mang đến cho bạn những trải nghiệm khó quên.",
                            "Windows 10 Home SL", "AMD Ryzen 5-5600H", "NVIDIA GeForce RTX3050, 4 GB", "8 GB, DDR4 2 khe", "512 GB SSD NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "3-cell Li-ion, 63 Wh", 10);
                    Product product18 = new Product("Laptop Dell Inspiron 15 3511", "DELL", 1109.39, "https://i.ibb.co/fCFc40S/image.png", "Laptop Dell Inspiron 15 3511 i7 (70267062) mang sức mạnh hiệu năng vượt trội, cân mọi tác vụ nặng nhờ sở hữu con chip Intel thế hệ 11 tân tiến cùng thiết kế hiện đại, sang trọng và bền bỉ.",
                            "Windows 10 Home SL", "Intel Core i7 Tiger Lake-1165G7", "NVIDIA GeForce MX350, 2 GB", "8 GB, DDR4 2 khe", "512 GB SSD NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "3-cell Li-ion, 41 Wh", 10);
                    Product product19 = new Product("Laptop Dell Vostro 5410", "DELL", 992.11, "https://i.ibb.co/DDgL6Ys/image.png", "Khí thế mạnh mẽ toát lên bên ngoài của chiếc Dell Vostro 5410 i5 11320H (V4I5214W) sẵn sàng lấn át mọi đối thủ, cùng cấu hình vượt trội bên trong, là vũ khí đắc lực cùng bạn chinh chiến trên mọi mặt trận kể cả công việc hay giải trí.",
                            "Windows 10 Home SL", "Intel Core i5 Tiger Lake - 11320H", "Intel Iris Xe Graphics", "8 GB, DDR4 2 khe", "512 GB SSD NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "4-cell Li-ion, 54 Wh", 10);
                    Product product20 = new Product("Laptop Dell Inspiron 7400", "DELL", 1276.95, "https://i.ibb.co/ySDF1qq/image.png", "Mang đến kiểu dáng sang trọng và đẳng cấp, laptop Dell Inspiron 7400 i5 1135G7 (N4I5134W) với sức mạnh hiệu năng mạnh mẽ từ chip Intel Gen 11, là cộng sự lý tưởng ở cả công việc và giải trí.",
                            "Windows 10 Home SL", "Intel Core i5 Tiger Lake-1135G7", "NVIDIA GeForce MX350, 2 GB", "16 GB, DDR4 (On board)", "512 GB SSD NVMe PCIe","Bluetooth 5.2, Wi-Fi 6 (802.11ax)", "4-cell Li-ion, 52 Wh", 10);


//                    Insert to table product
                    logger.info("Insert data: "+productRepository.save(product1));
                    logger.info("Insert data: "+productRepository.save(product2));
                    logger.info("Insert data: "+productRepository.save(product3));
                    logger.info("Insert data: "+productRepository.save(product4));
                    logger.info("Insert data: "+productRepository.save(product5));
                    logger.info("Insert data: "+productRepository.save(product6));
                    logger.info("Insert data: "+productRepository.save(product7));
                    logger.info("Insert data: "+productRepository.save(product8));
                    logger.info("Insert data: "+productRepository.save(product9));
                    logger.info("Insert data: "+productRepository.save(product10));
                    logger.info("Insert data: "+productRepository.save(product11));
                    logger.info("Insert data: "+productRepository.save(product12));
                    logger.info("Insert data: "+productRepository.save(product13));
                    logger.info("Insert data: "+productRepository.save(product14));
                    logger.info("Insert data: "+productRepository.save(product15));
                    logger.info("Insert data: "+productRepository.save(product16));
                    logger.info("Insert data: "+productRepository.save(product17));
                    logger.info("Insert data: "+productRepository.save(product18));
                    logger.info("Insert data: "+productRepository.save(product19));
                    logger.info("Insert data: "+productRepository.save(product20));
                }
            }
        };
    }
}
