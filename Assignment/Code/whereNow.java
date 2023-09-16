import java.util.*;
import java.io.*;

public class whereNow
{
    public static void main(String[] args)
    {
        DSAGraph graph = new DSAGraph();
        menu(graph);

    }

    //Main menu for the graph, this is where all the modifications to the graph will be made.
    public static void menu(DSAGraph graph)
    {
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        while(!done)
        {
            System.out.println("Main Menu please choose an option:\n(1)Load input file\n(2)Node Operations\n(3)Edge Operations\n(4)Parameter Tweaks\n(5)Display Graph\n(6)Display World\n(7)Enter Journey Details\n(8)Generate Routes\n(9)Display Routes\n(10)Save Network\n(11)Finish");
            String userInput = sc.nextLine();
            int choice = Integer.parseInt(userInput);

            switch(choice)
            {
                case 1:
                    try{
                        loadVertex("Map.txt", graph);
                        loadEdges("Map.txt", graph);
                    }catch(Exception e){System.out.println(e.getMessage());}
                    break;
                case 2:
                    nodeOperations(graph);
                    break;
                case 3:
                    edgeOperations(graph);
                    break;
                case 4:
                    break;
                case 5:
                    graph.displayAsMatrix();
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    System.out.println("Program Ending");
                    done = true;
                    break;
                default:
                    System.out.println("ERROR INPUT");
            }
        }
        sc.close();
    }

    //Folowing 2 methods are for reading the file and gathering all the vertexes/nodes for the graph
    public static void loadVertex(String fileName, DSAGraph graph) throws IOException
    {
        FileInputStream fileStrm = new FileInputStream(fileName);
        BufferedReader buffRdr = new BufferedReader(new InputStreamReader(fileStrm));

        String line;
        
        while((line = buffRdr.readLine()) != null)
        {
            
            if(line.charAt(0) != '#')
            {
                String[] lines = line.split("\\|");
                generateVertexes(lines[0], graph);               
            }
        }
        fileStrm.close();
    }

    //Gets all vertexes mentioned in the file and adds them to the graph.
    public static void generateVertexes(String inString, DSAGraph graph)
    {       
        if(inString.contains("<>"))
        {
            String[] vertexes = inString.split("<>");
            try{
                graph.addVertex(vertexes[0]);
            } catch(Exception e){}

            try{
                graph.addVertex(vertexes[1]);
            } catch(Exception e){}
            
        }
        else if(inString.contains("<"))
        {
            inString.split("<");
            String[] vertexes = inString.split("<");
            try{
                graph.addVertex(vertexes[0]);
            } catch(Exception e){}

            try{
                graph.addVertex(vertexes[1]);
            } catch(Exception e){}
        }
        else
        {
            inString.split(">");
            String[] vertexes = inString.split(">");
            try{
                graph.addVertex(vertexes[0]);
            } catch(Exception e){}

            try{
                graph.addVertex(vertexes[1]);
            } catch(Exception e){}
        }
    }

    //Folowing methods are for reading the file and gathering all the vedges for the graph
    //Reads over the file again and generates the appropriate edge with parameteres
    public static void loadEdges(String fileName, DSAGraph graph) throws IOException
    {
        FileInputStream fileStrm = new FileInputStream(fileName);
        BufferedReader buffRdr = new BufferedReader(new InputStreamReader(fileStrm));

        String line;
        
        while((line = buffRdr.readLine()) != null)
        {
            
            if(line.charAt(0) != '#')
            {
                String[] lines = line.split("\\|");
                int distance = processDistance(lines[1]);
                DSALinkedList security = processTraits(lines[2]);
                DSALinkedList barriers = processTraits(lines[3]);
                generateEdges(lines[0], graph, distance, security, barriers); //Uses information gathered from 
            }
        }
        fileStrm.close();
    }

    //Extracts the distance integer from the incoming string
    public static int processDistance(String inString)
    {
        int distance;
        distance = Integer.parseInt(inString.substring(2));
        return distance;
    }

    //Used to process the input files traits for each edge
    public static DSALinkedList processTraits(String inString)
    {
        DSALinkedList List = new DSALinkedList();
        String str = inString.substring(2);
        String[] strArr = str.split(",");
        for(String s : strArr)
        {
            List.insertLast(s);
        }
        return List;
    }

    public static void generateEdges(String inString, DSAGraph graph, int distance, DSALinkedList security, DSALinkedList barriers)
    {
        if(inString.contains("<>"))
        {
            String[] vertexes = inString.split("<>");   //connection in both directions
            try{
                graph.addEdge(vertexes[0], vertexes[1], distance, security, barriers);
                graph.addEdge(vertexes[1], vertexes[0], distance, security, barriers);
            } catch(Exception e){}           
        }
        else if(inString.contains("<"))
        {
            inString.split("<");
            String[] vertexes = inString.split("<");    //to|from
            try{
                graph.addEdge(vertexes[1], vertexes[0], distance, security, barriers);
            } catch(Exception e){}
        }
        else
        {
            inString.split(">");
            String[] vertexes = inString.split(">");    //from|to
            try{
                graph.addEdge(vertexes[0], vertexes[1], distance, security, barriers);
            } catch(Exception e){}
        }
    }

    //Menu containing all the possible node operations 
    public static void nodeOperations(DSAGraph graph)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Node Operation:\n(1)find\n(2)Insert\n(3)Delete\n(4)Update");
        int choice = Integer.parseInt(sc.nextLine());

        switch(choice)
        {
            case 1:
                findNode(graph);
                break;
            case 2:
                insertNode(graph);
                break;
            case 3:
                deleteNode(graph);
                break;
            case 4:
                updateNode(graph);
                break;
            default:
            System.out.println("ERROR INPUT");
        }

    }

    public static void findNode(DSAGraph graph)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the node you wish to find: ");
        String findNode = sc.nextLine();

        if(graph.hasVertex(findNode))
        {
            System.out.println("Node exists");
        }
        else
        {
            System.out.println("Node does not exist");
        }


    }

    public static void insertNode(DSAGraph graph)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name of the node you wish to insert: ");
        String newNode = sc.nextLine();
        graph.addVertex(newNode);


    }

    public static void deleteNode(DSAGraph graph)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name of the node you wish to delete: ");
        String deleteNode = sc.nextLine();
        graph.removeVertex(deleteNode);


    }

    public static void updateNode(DSAGraph graph)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name of the node you wish to update: ");
        String updateNode = sc.nextLine();
        System.out.println("Please enter the new name of the node: ");
        String newNodeName = sc.nextLine();
        graph.updateNode(updateNode, newNodeName);

    }

    //Menu containing the edge operations 
    public static void edgeOperations(DSAGraph graph)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Edge Operation:\n(1)find\n(2)Insert\n(3)Delete\n(4)Update");
        String userInput = sc.nextLine();
        int choice = Integer.parseInt(userInput);
        switch(choice)
        {
            case 1:
                findEdge(graph);
                break;
            case 2:
                insertEdge(graph);
                break;
            case 3:
                //deleteEdge(graph);
                break;
            case 4:
                //updateEdge(graph);
                break;
            default:
            System.out.println("ERROR INPUT");
        }
    }

    public static void findEdge(DSAGraph graph)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the name of the node you wish find edeges on");
        String edge = sc.nextLine();

        graph.getEdgeDetails(edge);
    }

    public static void insertEdge(DSAGraph graph)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the node you would like to travel FROM");
        String from = sc.nextLine();
        System.out.println("Enter the node you would like to travel TO");
        String to = sc.nextLine();
        System.out.println("Enter the DISTANCE between nodes");
        String userInput = sc.nextLine();
        int distance = Integer.parseInt(userInput);
        System.out.println("Enter the security levels for the path (Multiple security levels separated by comma)");
        String security = sc.nextLine();
        DSALinkedList secList = new DSALinkedList();
        if(security == "")
        {
            secList = null;
        }
        else
        {
            secList = processTraits(security);
        }
         
        System.out.println("Enter the barriers for the path (Multiple barriers separated by comma)");
        String barriers = sc.nextLine();
        DSALinkedList barrierList = new DSALinkedList();
        if(barriers == "")
        {  
            barrierList = null;
        }
        else
        {
            barrierList = processTraits(barriers);
        }
        graph.addEdge(from, to, distance, secList, barrierList);
    }
}
