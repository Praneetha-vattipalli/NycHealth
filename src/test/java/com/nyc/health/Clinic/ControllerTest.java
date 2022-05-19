package com.nyc.health.Clinic;


import com.nyc.health.Clinic.Controller.ClinicRestController;
import com.nyc.health.Clinic.EntityClinic.Clinic;
import com.nyc.health.Clinic.Repository.ClinicRepo;
import com.nyc.health.Clinic.Service.ServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


//@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {ControllerTest.class})
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock
    ServiceImpl serviceimpl;


    @InjectMocks
    ClinicRestController clinicRestController;

    List<Clinic> clinics;
    private ClinicRepo clinicRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    //This is for  get List of clinics
    @Test
    public void test_AllClinics() {

        clinics = new ArrayList<>();
        clinics.add(new Clinic(1, "praneetha", "Farminton", "p@gmail.com", "313-539-6241","Closed","2022-02-13","2022-3-13"));
        Mockito.when(serviceimpl.getAllClinic()).thenReturn(clinics);

            Assert.assertEquals(1, clinicRestController.viewAllClinic().getBody().size());


    }
    @Test(expected = NullPointerException.class)
    @Order(2)
    public void test_AllClinicswithException () {
        when(serviceimpl.getAllClinic()).thenThrow(NullPointerException.class);
        clinicRestController.viewAllClinic();
    }
    //This is for add clinic
    @Test
    @Order(3)
    public void test_addClinic() {
        Clinic c = new Clinic(2, "sandeep", "novi", "ps@gmail.com", "313-539-6242","Closed","2022-02-13","2022-3-13");

        when(serviceimpl.registerNewClinic(c)).thenReturn(c);
        Assertions.assertEquals(c, clinicRestController.saveClinic(c).getBody());
    }
    @Test(expected = RuntimeException.class)
    @Order(4)
    public void test_addClinicswithException () {
        Clinic c = new Clinic();
        when(serviceimpl.registerNewClinic(c)).thenThrow(RuntimeException.class);
        clinicRestController.saveClinic(c);
    }

    //This is for get by  clinicID
    @Test
    @Order(5)
    public void getClinicById() {
        int id = 2;
        Clinic c = new Clinic(2, "sandeep", "novi", "ps@gmail.com", "313-539-6242","Closed","2022-02-13","2022-3-13");
        when(serviceimpl.getClinicById(id)).thenReturn(c);
        assertEquals(c, clinicRestController.getClinicById(id).getBody());


    }
    @Test(expected = NoResultException.class)
    @Order(6)
    public void test_getClinicByIdwithException () {
        int id = -1;
        Clinic c = new Clinic(-1, "deepika", "sothlyon", "dn@gmail.com", "313-539-6244","Closed","2022-02-13","2022-3-13");
        when(serviceimpl.getClinicById(id)).thenThrow(NoResultException.class);
        when(clinicRestController.getClinicById(id)).thenThrow(NoResultException.class);
    }
    //This is for update clinic
    @Test
    @Order(7)
    public void test_UpdateByClinicid() {
        int id = 2;
        Clinic c = new Clinic(2, "pradeep", "novi", "ps@gmail.com", "313-539-6242","Closed","2022-02-13","2022-3-13");

        when(serviceimpl.updateClinicRecord(c, id)).thenReturn(c);

        ResponseEntity<String> res = clinicRestController.updateClinic(id, c);

        assertEquals("Clinic Record with id 2 has updated", res.getBody());


    }
    @Test(expected = NoResultException.class)
    @Order(8)
    public void test_UpdateClinicByIdwithException () {
        int id =-1;
        Clinic c = new Clinic(-1, "pradeep", "novi", "ps@gmail.com", "313-539-6242","Closed","2022-02-13","2022-3-13");
        when(serviceimpl.updateClinicRecord(c, id)).thenThrow(NoResultException.class);
        //Clinic clinic= serviceImple.updateClinicRecord(c,id);


        when(clinicRestController.updateClinic(id,c)).thenThrow(NoResultException.class);
    }


    //This is for delete clinic
    @Test
    @Order(9)
    public void test_deleteClinic() {
        int id = 2;
   doNothing().when(serviceimpl).deleteClinic(id);
   serviceimpl.deleteClinic(2);
   verify(serviceimpl,times(1)).deleteClinic(2);
    }
    @Test(expected = NoResultException.class)
    @Order(10)
    public void test_deleteClinicServiceWithException(){
        int id=0;
        doThrow(NoResultException.class).when(serviceimpl).deleteClinic(id);
        clinicRestController.deleteClinic(id);
    }
}