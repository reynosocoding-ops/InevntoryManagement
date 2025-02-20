package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.plaf.metal.OceanTheme;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;
    private final InhousePartRepository inhousePartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository, InhousePartRepository inhousePartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
        this.inhousePartRepository = inhousePartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if((inhousePartRepository.count() + outsourcedPartRepository.count() + productRepository.count()) <= 0) {
            InhousePart monitor = new InhousePart();
            monitor.setId(1);
            monitor.setName("Monitor");
            monitor.setInv(5);
            monitor.setPrice(30.00);
            monitor.setMaxInv(50);
            monitor.setMinInv(10);
            inhousePartRepository.save(monitor);

            InhousePart mouse = new InhousePart();
            mouse.setId(2);
            mouse.setName("Mouse");
            mouse.setInv(8);
            mouse.setPrice(20.00);
            mouse.setMaxInv(50);
            mouse.setMinInv(10);
            inhousePartRepository.save(mouse);

            InhousePart keyboard = new InhousePart();
            keyboard.setId(3);
            keyboard.setName("Keyboard");
            keyboard.setInv(10);
            keyboard.setPrice(30.00);
            keyboard.setMaxInv(50);
            keyboard.setMinInv(10);
            inhousePartRepository.save(keyboard);

            InhousePart compTower = new InhousePart();
            compTower.setId(4);
            compTower.setName("Computer Tower");
            compTower.setInv(5);
            compTower.setPrice(300.00);
            compTower.setMaxInv(50);
            compTower.setMinInv(10);
            inhousePartRepository.save(compTower);

            InhousePart wifiRouter = new InhousePart();
            wifiRouter.setId(5);
            wifiRouter.setName("Wifi Router");
            wifiRouter.setInv(6);
            wifiRouter.setPrice(60.00);
            wifiRouter.setMaxInv(50);
            wifiRouter.setMinInv(10);
            inhousePartRepository.save(wifiRouter);

            Product gamingPC = new Product("Gaming Computer",800,15);
            Product workPC = new Product("Work Computer",600,15);
            Product kidsPC= new Product("Kids Computer",400,15);
            Product budgetPC= new Product("Budget Computer",3000,15);
            Product performPC= new Product("High Performance Computer",2000,15);
            productRepository.save(gamingPC);
            productRepository.save(workPC);
            productRepository.save(kidsPC);
            productRepository.save(budgetPC);
            productRepository.save(performPC);


        }
       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        /*
        Product bicycle= new Product("bicycle",100.0,15);
        Product unicycle= new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
