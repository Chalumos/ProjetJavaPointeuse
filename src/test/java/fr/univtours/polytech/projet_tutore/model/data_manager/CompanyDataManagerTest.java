package fr.univtours.polytech.projet_tutore.model.data_manager;

import fr.univtours.polytech.projet_tutore.model.Stub;
import fr.univtours.polytech.projet_tutore.model.company.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

public class CompanyDataManagerTest {
    private CompanyDataManager companyDataManager;

    @BeforeEach
    void setUp() {
        companyDataManager = new CompanyDataManager();
    }

    @Test
    void testSerialize() {
        ArrayList<Company> companyList =new ArrayList<>();
        ArrayList<Company> newCompanyList = new ArrayList<>();
        Company company = Stub.generateCompany();

        String path = (new File(companyDataManager.getFilePath())).getParentFile().getParentFile().getPath();
        path += File.separator + "data_test" + File.separator + "CompanyTest.txt";

        companyList.add(company);

        try {
            companyDataManager.setFilePath(path);
            companyDataManager.serialize(companyList);
            newCompanyList.addAll(companyDataManager.parse());

            Assertions.assertEquals(company.toString(), newCompanyList.get(0).toString());
        }
        catch(Exception e){
            e.printStackTrace();
            Assertions.fail();
        }
    }
}
