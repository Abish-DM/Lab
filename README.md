

### Experiment 1 — VMware/VirtualBox Installation

Aim: To install virtualization software and create a virtual machine.

Procedure and commands

* Install VMware Workstation or VirtualBox.

* Download an Ubuntu ISO from the official Ubuntu website.

* Open VMware and select Create a New Virtual Machine.

* Select the downloaded ISO file.

* Configure the VM name, username, password, RAM and disk capacity.

* Finish the configuration and start the VM.

Useful VirtualBox commands (if VirtualBox is installed):

```bash
VBoxManage list vms
VBoxManage list runningvms
```

Expected output: Ubuntu boots successfully inside the virtual machine.

### Experiment 2 — Install C Compiler and Execute a Program

Aim: To install GCC and compile and execute a C program in Ubuntu.

Commands

Install GCC:

```bash
sudo apt update
sudo apt install gcc
gcc --version
```

Create the program:

```bash
nano even.c
```

Enter this program:

```c
#include <stdio.h>
int main() {
    int n;
    scanf("%d", &n);
    if (n % 2 == 0)
        printf("Even\n");
    else
        printf("Odd\n");
    return 0;
}
```

Save with `Ctrl+O`, press Enter, then exit with `Ctrl+X`.

Compile and execute:

```bash
gcc even.c -o even
./even
```

Sample output

```text
8
Even
```

Result: The C compiler is installed, and the program executes successfully.

### Experiment 3 — Google App Engine Hello World Application

Aim: To create and run a simple web application using Google App Engine.

Procedure

* Install Python and the Google Cloud SDK, as required by the lab environment.

* Sign in and select or create a cloud project.

* Create the application files.

* Configure the application using `app.yaml`.

* Run the application locally or deploy it using the supported SDK.

Useful commands for a modern Google Cloud environment

```bash
gcloud --version
gcloud auth login
gcloud config set project YOUR_PROJECT_ID
gcloud app deploy
```

To run a compatible local Python web application, use its framework's development command. For a Flask app, for example:

```bash
python -m pip install flask
python app.py
```

Expected output: A Hello World message is displayed in the browser.

Compatibility note: The PDF uses an older App Engine SDK workflow. The commands above are modern alternatives and may require adapting the application configuration; they are not identical to the original lab procedure.

### Experiment 4 — Use GAE Launcher to Launch a Web Application

Aim: To launch a web application using Google App Engine tools.

Procedure

* Install the required Python runtime and compatible App Engine tools.

* Create the project directory.

* Create `app.yaml` and `index.py`.

* Configure the application in the launcher or supported development server.

* Start the application and open its local URL.

Useful commands

```bash
mkdir hello-app
cd hello-app
python --version
```

For a compatible Flask application:

```bash
python -m pip install flask
python app.py
```

Expected output: The web application opens in the browser and displays its output.

Important: GAE Launcher and Python 2.7 are legacy technologies. The original launcher procedure may require an old lab image. Do not assume the modern Flask commands reproduce the exact legacy launcher behavior.

### Experiment 5 — CloudSim Cloud Scheduling Simulation

Aim: To simulate a cloud computing environment and execute tasks using CloudSim.

Procedure

* Open the Java IDE containing the CloudSim library.

* Create a Java class and import the CloudSim packages.

* Initialize CloudSim.

* Create a datacenter and broker.

* Create VMs and cloudlets.

* Submit the VMs and cloudlets to the broker.

* Start the simulation and display the results.

Code checkpoints

Your program should include these operations:

```java
CloudSim.init(1, Calendar.getInstance(), false);
```

```java
broker.submitVmList(vmList);
broker.submitCloudletList(cloudletList);
```

```java
CloudSim.startSimulation();
CloudSim.stopSimulation();
```

Then print the received cloudlets' status, start time, finish time and execution details.

Expected output: The simulation runs, and the cloudlet execution status and timing information are displayed.

Viva point: A cloudlet represents a task, a VM provides the execution environment, and a broker manages the submission of VMs and cloudlets.

These are essential code checkpoints, not a complete replacement for the original CloudSim program. Your record uses a custom datacenter creation method, so preserve that method and its scheduling configuration.

### Experiment 6 — Transfer Files Between Virtual Machines

Aim: To transfer files between two virtual machines using a shared folder.

Procedure

* Create a folder on the host computer.

* Create a test file inside it.

* Enable the folder under VM settings → Shared Folders.

* Start the first Ubuntu VM and install the guest utilities if needed.

* Mount or access the shared folder.

* Edit the file from the first VM.

* Open the same folder from the second VM and verify the changes.

Commands for VMware on Ubuntu

Install the guest utilities if required:

```bash
sudo apt update
sudo apt install open-vm-tools open-vm-tools-desktop
```

Check the shared-folder mount:

```bash
vmware-hgfsclient
```

Mount the shared folder if it is not mounted already:

```bash
sudo mkdir -p /mnt/hgfs
sudo vmhgfs-fuse .host:/ /mnt/hgfs -o allow_other
```

List the shared folders:

```bash
ls /mnt/hgfs
```

Create and read a test file:

```bash
echo "Cloud Computing Lab" > /mnt/hgfs/SharedFolder/test.txt
cat /mnt/hgfs/SharedFolder/test.txt
```

Replace `SharedFolder` with the actual shared-folder name.

Expected output: The test file is visible from both VMs, and changes made through the shared folder are accessible from the other VM.

Note: The mount command and permissions depend on how the folder was configured. If the shared folder is already mounted, you do not need to mount it again.

### Experiment 7 — Launch a Virtual Machine Using TryStack/OpenStack

Aim: To study OpenStack and launch a virtual machine through the OpenStack dashboard.

Procedure

* Install and configure the OpenStack dashboard in the provided lab environment.

* Configure the dashboard settings.

* Restart Apache.

* Log in to the OpenStack dashboard.

* Navigate to Project → Compute → Instances.

* Click Launch Instance.

* Configure the instance name, image, flavor, network, security group and SSH key.

* Launch the instance and open its console.

Commands shown by the lab workflow

Install the dashboard package on a compatible Ubuntu/OpenStack environment:

```bash
sudo apt update
sudo apt install openstack-dashboard
```

Open the dashboard configuration file:

```bash
sudo nano /etc/openstack-dashboard/local_settings.py
```

After configuring the appropriate settings, restart Apache:

```bash
sudo systemctl restart apache2
sudo systemctl status apache2
```

Expected output: The OpenStack dashboard is accessible, and a virtual machine reaches the active/running state.

Important: Installing the dashboard package alone does not install a complete OpenStack cloud. This experiment requires a working OpenStack backend and compatible configuration. The TryStack demo environment described in the record may no longer be available.

### Experiment 8(a) — Install a Hadoop Single-Node Cluster

Aim: To install and configure Hadoop on Ubuntu as a single-node cluster.

Procedure and commands

### 1\. Update packages and install Java

```bash
sudo apt update
sudo apt install openjdk-11-jdk ssh rsync
java -version
javac -version
```

Check whether SSH is available:

```bash
sudo systemctl enable --now ssh
```

### 2\. Configure Hadoop

Download and extract the Hadoop version required by your lab. Then configure the `JAVA_HOME` environment variable and Hadoop configuration files.

Check the installation:

```bash
hadoop version
```

### 3\. Configure HDFS and format the NameNode

After configuring `core-site.xml`, `hdfs-site.xml`, and the required environment variables, initialize the filesystem:

```bash
hdfs namenode -format
```

Run this format command only when initializing a new, empty Hadoop filesystem. Do not run it on an existing cluster containing data.

### 4\. Start Hadoop services

```bash
start-dfs.sh
start-yarn.sh
```

Check the Java processes:

```bash
jps
```

Typical processes include `NameNode`, `DataNode`, `SecondaryNameNode`, `ResourceManager`, and `NodeManager`, depending on the services configured.

### 5\. Verify HDFS

```bash
hdfs dfs -mkdir -p /user/$USER/input
hdfs dfs -ls /
```

Expected output: Hadoop starts successfully, and the HDFS directory listing is displayed.

Compatibility note: Your record uses Hadoop 2.9.0 and Java 11. Match the Java and Hadoop versions supplied in your lab VM if the scripts or configuration require them.

### Experiment 8(b) — Run a Hadoop WordCount Application

Aim: To execute a WordCount program using the installed Hadoop single-node cluster.

Procedure

* Verify Hadoop and Java installation.

* Create a text file containing sample words.

* Upload the input file to HDFS.

* Execute the WordCount MapReduce program.

* Display the output from HDFS.

### 1\. Check the installed versions

```bash
hadoop version
javac -version
```

### 2\. Create an input file

```bash
echo "cloud computing cloud lab" > input.txt
```

### 3\. Create the HDFS input directory and upload the file

```bash
hdfs dfs -mkdir -p /user/$USER/input
hdfs dfs -put -f input.txt /user/$USER/input/
hdfs dfs -ls /user/$USER/input
```

### 4\. Run WordCount

If the compiled WordCount JAR is named `wordcount.jar` and its main class and configuration are correct:

```bash
hadoop jar wordcount.jar WordCount /user/$USER/input /user/$USER/output
```

If the output directory already exists, remove it before rerunning:

```bash
hdfs dfs -rm -r /user/$USER/output
```

### 5\. Display the result

```bash
hdfs dfs -cat /user/$USER/output/part-r-00000
```

Sample output

For the input `cloud computing cloud lab`, the output should contain word counts equivalent to:

```text
cloud       2
computing   1
lab         1
```

The exact order may vary.

Important: The above execution command assumes that the WordCount Java source has already been compiled into the correct JAR. The JAR name, main class, and build procedure must match the actual program provided in your lab record.


