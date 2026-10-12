import org.cloudbus.cloudsim.*;
import org.cloudbus.cloudsim.core.CloudSim;
import java.util.*;

public class CloudSimTest {
    public static void main(String[] args) {
        try {
            CloudSim.init(1, Calendar.getInstance(), false);

            DatacenterBroker broker =
                new DatacenterBroker("Broker");
            int id = broker.getId();

            List<Vm> vms = new ArrayList<>();
            vms.add(new Vm(0, id, 1000, 1, 512,
                1000, 10000, "Xen",
                new CloudletSchedulerTimeShared()));
            broker.submitVmList(vms);

            List<Cloudlet> tasks = new ArrayList<>();
            UtilizationModel u = new UtilizationModelFull();

            Cloudlet c = new Cloudlet(0, 40000, 1,
                300, 300, u, u, u);
            c.setUserId(id);
            tasks.add(c);
            broker.submitCloudletList(tasks);

            CloudSim.startSimulation();
            CloudSim.stopSimulation();

            for (Cloudlet t : broker.getCloudletReceivedList())
                System.out.println("Cloudlet " + t.getCloudletId()
                    + " Status: " + t.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}