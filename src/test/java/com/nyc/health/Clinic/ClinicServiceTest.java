package com.nyc.health.Clinic;

import com.nyc.health.Clinic.EntityClinic.Clinic;
import com.nyc.health.Clinic.Repository.ClinicRepo;
import com.nyc.health.Clinic.Service.ServiceImpl;
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

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@SpringBootTest(classes = {ClinicServiceTest.class})
@RunWith(MockitoJUnitRunner.class)
public class ClinicServiceTest {
    @Mock
    ClinicRepo clinicRepo;


    @InjectMocks
    ServiceImpl serviceImple;
    List<Clinic> clinics;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @Order(1)
    public void test_AllClinicsService() {

        clinics = new ArrayList<>();
        assertTrue(clinics.add(new Clinic(1, "apolo", "Farminton", "p@gmail.com", "313-539-6241", "closed", "2022 - 9 - 12", "2022 - 10 - 13")));
        when(clinicRepo.findAll()).thenReturn(clinics);

            assertEquals(clinics, serviceImple.getAllClinic());

    }
    @Test(expected = NullPointerException.class)
    @Order(2)
    public void test_AllClinicsServicewithException () {
        when(clinicRepo.findAll()).thenThrow(NullPointerException.class);
        serviceImple.getAllClinic();
    }
    @Test
    @Order(3)
    public void test_addClinicService() {
        Clinic c = new Clinic(3, "MD.Anderson", "sothlyon", "dn@gmail.com", "313-539-6244","closed","2022-9-22","2022-10-13");

        when(clinicRepo.save(c)).thenReturn(c);
        Assertions.assertEquals(c,serviceImple.registerNewClinic(c));
    }
    @Test(expected = RuntimeException.class)
    @Order(4)
    public void test_addClinicsServicewithException () {
        Clinic c = new Clinic();
        when(clinicRepo.save(c)).thenThrow(RuntimeException.class);
        serviceImple.registerNewClinic(c);
    }
//
//
    @Test
    @Order(5)
    public void getClinicByIdService(){
        int id=3;
        Clinic c = new Clinic(3, "MD.Anderson", "sothlyon", "dn@gmail.com", "313-539-6244","closed","2022-9-22","2022-10-13");
        when(clinicRepo.findById(id)).thenReturn(Optional.of(c));
        assertEquals(c, serviceImple.getClinicById(id));


    }
    @Test(expected = NoResultException.class)
    @Order(6)
    public void test_getClinicByIdServicewithException () {
        int id = -1;
        when(clinicRepo.findById(id)).thenThrow(NoResultException.class);
        when(serviceImple.getClinicById(id)).thenThrow(NoResultException.class);
    }



    @Test
    @Order(7)
    public void test_UpdateByClinicidService(){
        int id = 2;
        Clinic c = new Clinic(2, "MD.Anderson", "novi", "ps@gmail.com", "313-539-6242","closed","2022-9-22","2022-10-13");

        when(clinicRepo.findById(2)).thenReturn(Optional.of(c));

       Clinic clinic= serviceImple.updateClinicRecord(c,id);


        Mockito.verify(clinicRepo,times(1)).findById(id);

    }
    @Test(expected = NoResultException.class)
    @Order(8)
    public void test_UpdateClinicByIdServicewithException () {
        int id =-1;
        Clinic c = new Clinic(-1, "MD.Anderson", "novi", "ps@gmail.com", "313-539-6242","open","2022-9-22","2022-10-13");
        when(clinicRepo.findById(id)).thenThrow(NoResultException.class);
        //Clinic clinic= serviceImple.updateClinicRecord(c,id);


        when(serviceImple.updateClinicRecord(c,id)).thenThrow(NoResultException.class);
    }
@Test(expected = NoResultException.class)
@Order(9)
    public void test_deleteClinicServiceWithException(){
        int id=0;
        //doNothing().when(clinicRepo).deleteById(id);
        //clinicRepo.deleteById(id);

        doThrow(NoResultException.class).when(clinicRepo).deleteById(id);
    clinicRepo.deleteById(id);
    }

    @Test
    @Order(10)
    public void test_deleteClinicService() {
        int id = 2;
   doNothing().when(clinicRepo).deleteById(id);
   clinicRepo.deleteById(2);
   Mockito.verify(clinicRepo,times(1)).deleteById(2);
    }


}

