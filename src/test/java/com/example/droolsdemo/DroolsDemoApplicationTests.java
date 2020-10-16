package com.example.droolsdemo;

import com.trusfort.risk.base.obj.AuditObject;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class DroolsDemoApplicationTests {
    private static KieContainer container = null;
    private KieSession statefulKieSession = null;
    @Test
    void contextLoads() {
        System.setProperty("drools.dialect.mvel.strict","false");
        KieServices kieServices = KieServices.Factory.get();
        container = kieServices.getKieClasspathContainer();
        statefulKieSession = container.newKieSession("all-rules");
//        Person person = new Person();
//        person.setAge(12);
//        person.setName("Test");
        AuditObject auditObject = new AuditObject();
        auditObject.put("list", Arrays.asList("1","2","3"));
        auditObject.put("createTimeLong",System.currentTimeMillis());
//        statefulKieSession.insert(person);
        statefulKieSession.insert(auditObject);
        statefulKieSession.fireAllRules();
        statefulKieSession.dispose();
    }
}
