# 8th Exercise

## 1. 📦 Difference between virtual machine and Docker container

- Virtual machine is a software-defined emulation of a physical computer
- It runs it own operating system
- Acting like a completely independent machine while sharing the underlying physical hardware and resources (CPU, RAM, storage) of an actual computer
- By using the resources of a single physical machine—memory, CPU, network interface and storage—VMs enable businesses to run multiple machines virtually (with different operating systems) on a single device (Source: https://www.ibm.com/think/topics/virtual-machines)
- VMs are typically referred to as guests, with one or more “guest” machines running on a physical machine called the “host” machine
- VM technology includes virtual servers, virtual server instances (VSIs) and virtual private servers (VPSs)

- Eine VM virtualisiert komplette Hardware inkl. Betriebssystem über einen Hypervisor
- mit eigenem Kernel; Virtual Machine kann bis zu mehreren GBs groß sein

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

- Containers are standardized, executable components that combine application source code with the operating system (OS) libraries and dependencies required to run that code in any environment
- Unlike VMs, containers don't carry the payload of an entire OS instance and hypervisor
- They include just the OS processes and dependencies necessary to run the code
- Container sizes are measured in megabytes (versus gigabytes for some VMs), make better use of hardware capacity and have faster startup times
- Containerized applications can be written once and run anywhere

- Im Gegensatz zu VMs tragen Container nicht die Last einer vollständigen Betriebssysteminstanz und eines Hypervisors
- Sie enthalten nur die Betriebssystemprozesse und Abhängigkeiten, die zur Ausführung des Codes notwendig sind
- Container-Größen werden in Megabyte gemessen (im Vergleich zu Gigabyte bei manchen VMs), nutzen die Hardware-Kapazität effizienter und haben kürzere Startzeiten



## 2. What is the main purpose of Continunous Integration?

- c) frequently integrated code into a shared repository and run automated test
- CI bedeutet, dass Entwickler regelmäßig Code in ein gemeinsames Repository pushen (z. B. GitHub) -> woraufhin automatisch Tests ausgeführt werden, um Integrationsfehler frühzeitig zu erkennen



## 4.Which tool is commonly used to orchestrate CICD pipelines?

- 

### ✍️ Authors

Mention who you are and link to your GitHub or organization's website.


## 🚀 Usage

*Show off what your software looks like in action! Try to limit it to one-liners if possible and don't delve into API specifics.*

```py
>>> import mypackage
>>> mypackage.do_stuff()
'Oh yeah!'
```


## ⬇️ Installation

Simple, understandable installation instructions!

```bash
pip install my-package
```

And be sure to specify any other minimum requirements like Python versions or operating systems.

*You may be inclined to add development instructions here, don't.*


## 💭 Feedback and Contributing

Add a link to the Discussions tab in your repo and invite users to open issues for bugs/feature requests.

This is also a great place to invite others to contribute in any ways that make sense for your project. Point people to your DEVELOPMENT and/or CONTRIBUTING guides if you have them.
