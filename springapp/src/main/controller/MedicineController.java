import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/medicines")  // Make sure the controller maps to "/medicines"
public class MedicineController {
    private Map<Integer, Medicine> medicineStore = new ConcurrentHashMap<>();
    private AtomicInteger idGenerator = new AtomicInteger(1);

    @PostMapping
    public boolean addMedicine(@RequestBody Medicine medicine) {
        int medicineId = idGenerator.getAndIncrement();
        medicine.setMedicineId(medicineId);
        medicineStore.put(medicineId, medicine);
        return true;
    }

    @PutMapping("/{medicineId}")
    public Medicine updateMedicine(@PathVariable int medicineId, @RequestBody Medicine updatedMedicine) {
        if (medicineStore.containsKey(medicineId)) {
            updatedMedicine.setMedicineId(medicineId);
            medicineStore.put(medicineId, updatedMedicine);
            return updatedMedicine;
        }
        return null; // Medicine not found
    }
}
