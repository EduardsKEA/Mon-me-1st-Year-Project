package dk.kea.dat16j.therussians.moname.domain.webservice;

import dk.kea.dat16j.therussians.moname.domain.entity.Treatment;
import dk.kea.dat16j.therussians.moname.domain.repository.TreatmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Chris on 07-May-17.
 */

@Controller    // This means that this class is a Controller
//@RestController @Controller vs @RestController?
@RequestMapping(path = "/treatments") // This means URL's start with /treatments (after Application path)
public class TreatmentController {

    @Autowired // This means to get the bean called treatmentRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    public TreatmentController(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    private TreatmentRepository treatmentRepository;

    @RequestMapping(path = "/add") // Map ONLY GET Requests
    @ResponseBody
    public String addNewTreatment(@RequestParam String name, @RequestParam float price, @RequestParam long duration, @RequestParam String description) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        Treatment t = new Treatment();
        t.setName(name);
        t.setDuration(duration);
        t.setPrice(price);
        t.setDescription(description);

        treatmentRepository.save(t);
        return "Saved";
    }

    @ResponseBody
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public Iterable<Treatment> getAllTreatments() {
        // This returns a JSON or XML with the users
        return treatmentRepository.findAll();
    }
}
