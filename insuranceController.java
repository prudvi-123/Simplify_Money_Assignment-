package insurance.intern;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;

    // Show Insurance List
    @GetMapping("/list")
    public String showInsuranceList(Model model) {
        model.addAttribute("insurances", insuranceService.getAllInsurances());
        return "insurance";
    }

    // Handle Buy Request and Show Success Page
    @PostMapping("/buy")
    public String buyInsurance(@RequestParam("insuranceId") String insuranceId, Model model) {
        Optional<Insurance> insurance = insuranceService.getInsuranceById(insuranceId);
        if (insurance.isPresent()) {
            model.addAttribute("insurance", insurance.get());
            return "success";
        } else {
            return "redirect:/insurance/list";
        }
    }

    // Generate PDF Receipt and Download
    @PostMapping("/download-receipt")
    public ResponseEntity<ByteArrayResource> downloadReceipt(@RequestParam("insuranceId") String insuranceId) {
        Optional<Insurance> insurance = insuranceService.getInsuranceById(insuranceId);
        if (insurance.isPresent()) {
            try {
                // Create PDF document
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PdfWriter writer = new PdfWriter(outputStream);
                PdfDocument pdfDocument = new PdfDocument(writer);
                Document document = new Document(pdfDocument);

                // Add content to PDF
                document.add(new Paragraph("Insurance Purchase Receipt"));
                document.add(new Paragraph("-------------------------------"));
                document.add(new Paragraph("Insurance Name: " + insurance.get().getName()));
                document.add(new Paragraph("Type: " + insurance.get().getType()));
                document.add(new Paragraph("Price: $" + insurance.get().getPrice()));
                document.add(new Paragraph("\nThank you for your purchase!"));
                
                document.close();

                // Convert PDF to ByteArrayResource
                ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Receipt.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resource);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
