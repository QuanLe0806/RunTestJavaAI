package QuaSong;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet; // đây là cấu trúc dữ liệu cây nha 


// ở đây để tiện cho việc theo dõi nên viết chung 1 đống lại 
// chứ không viết như là lúc học lập trình hướng đối tượng
// viết như hđt sẽ không biết được cái hàm đó từ đâu chui ra 

public class crossingRiver{

    // ở đây F là nông dân 
    // W là sói
    // S là cừu
    // C là bấp cải 
    // mọi người có thể đổi tên lại chỗ này tại lúc làm quên là bữa thầy cho con gì rồi

    private String[] moves = { "F", "FW", "FS", "FC" };
    private ArrayList<Node> queue;
    private ArrayList<Node> solutions;
    private Node root;

    public crossingRiver(){

        queue = new ArrayList<Node>();
        solutions = new ArrayList<Node>();
    }

    
    private class State{

        private String shore; 
        private TreeSet<String> left, right; 

        public State(String shore, TreeSet<String> left, TreeSet<String> right){
            this.shore = shore;
            this.left = left;
            this.right = right;
        }

        private boolean checkAllowShore(TreeSet<String> bank){
            if (bank.contains("W") && bank.contains("S") && (bank.contains("F") == false))
                return false;
            if (bank.contains("S") && bank.contains("C") && (bank.contains("F") == false))
                return false;
            return true;
        }


        public boolean isAllow(){
            if (checkAllowShore(left) && checkAllowShore(right))
                return true;
            else
                return false;
        }


        public boolean isSolution(){// kiểm tra cái lời giải là đúng hay sai 

            if (left.isEmpty() && right.contains("W") && right.contains("S") && right.contains("C")
                    && right.contains("F"))
                return true;
            else
                return false;
        }



        public State tranSits(String move){

            String nodeShore;
            TreeSet<String> nodeleft = new TreeSet<String>();
            TreeSet<String> noderight = new TreeSet<String>();

            if (shore.equalsIgnoreCase("left"))
                nodeShore = "right";
            else
                nodeShore = "left";

            copylist(right, noderight);
            copylist(left, nodeleft);

            for (int i = 0; i < move.length(); i++){
                String item = move.substring(i, i + 1);
                if (shore.equalsIgnoreCase("left"))
                {
                    if (nodeleft.remove(item))
                        noderight.add(item);
                    else
                        return null; 
                }
                else
                {
                    if (noderight.remove(item))
                        nodeleft.add(item);
                    else
                        return null; 
                }
            }

            return new State(nodeShore, nodeleft, noderight);

        }


        private void copylist(TreeSet<String> src, TreeSet<String> dst){
            for (String element : src)
                dst.add(element);
        }


        public boolean compare(State states){
            TreeSet<String> temp;

            if (!states.getShore().equalsIgnoreCase(shore))
                return false;

            temp = states.getLeft();
            for (String e : left){
                if (!temp.contains(e))
                    return false;
            }

            temp = states.getRight();
            for (String e : right){
                if (!temp.contains(e))
                    return false;
            }
            return true;
        }
        // mấy cái này chỉ là mấy cái get set như đã học thôi không có gì đặc biệt 
        // ghi chú này là phòng khi quên những gì đã học

        public String getShore(){ return shore; }
        public TreeSet<String> getLeft(){ return left; }
        public TreeSet<String> getRight(){ return right; }

        @Override
        public String toString(){
            StringBuffer strBuff = new StringBuffer();
            strBuff.append("{L:");
            for (String e : left)
                strBuff.append(e);
            strBuff.append(" ");
            strBuff.append("R:");
            for (String e : right)
                strBuff.append(e);
            strBuff.append("}");
            return strBuff.toString();
        }

    }


    private class Node{
        public Node parent; // node cha 
        public State data; // dữ liệu 
        public ArrayList<Node> chilNodeList; // mảng các node con
        public int level; // cấp bậc của node hoặc là độ sâu gọi nó là gì cũng được
        public String move; // các bước di chuyển 


        //  khởi tạo 1 node 
        public Node(State data){

            parent = null;
            this.data = data;
            chilNodeList = new ArrayList<Node>();
            level = 0;
            move = "";
        }

        public boolean isAncestor(){ // hàm này chỉ kiểm tra coi các node có phải tổ tiên hay con cái gì của nhau không 
            Node node = parent;
            boolean retur = false;
            while (node != null){
                if (data.compare(node.data))
                {
                    retur = true;
                    break;
                }
                node = node.parent;
            }
            return retur;
        }

    }


    public void startBreadthFirstSearch(){

        solutions = new ArrayList<Node>();  // khởi tạo cách giải 
        TreeSet<String> left = new TreeSet<String>();
        left.add("W");
        left.add("S");              // ở đây là cho các phần tử vào bờ bên trái nha 
        left.add("C");
        left.add("F");

        State inits = new State("left", left, new TreeSet<String>()); // khởi tạo trạng thái
        root = new Node(inits);
        root.level = 0;
        queue.add(root);
        while (!queue.isEmpty()){
            Node tempNode = queue.remove(0);
            //System.out.println("Processing Level " + tempNode.level + " " + tempNode.data);
            for (String movef : moves){
                State s = tempNode.data.tranSits(movef);

                if (s != null && s.isAllow()){ // đại khái là thử xem cái cách giải này  có vi phạm quy định đã nêu ở trên không 
                    Node child = new Node(s);
                    child.parent = tempNode;
                    child.level = tempNode.level + 1;
                    child.move = movef + " Di chuyển " + child.data.getShore();

                    if (!child.isAncestor()){
                        tempNode.chilNodeList.add(child);
                        if (child.data.isSolution() == false){
                            queue.add(child);
                            System.out.println("Thêm trạng thái " + child.data);
                        }
                        else{
                            solutions.add(child);
                            //System.out.println("Found solution " + child.data);
                        }
                    }
                }
            }
        }
    }


    public void startDepthFirstSearch(){

        int deeplimit = 1; // độ sâu hay cấp bậc tối đa
        solutions = new ArrayList<Node>(); // ở đây ta khởi tạo giải pháp rỗng 

        while (solutions.size() == 0 && deeplimit <= 10){

            TreeSet<String> left = new TreeSet<String>();
            left.add("W");
            left.add("S");
            left.add("C");
            left.add("F");

            State inits = new State("Trái", left, new TreeSet<String>());
            root = new Node(inits);
            root.level = 0;

            //System.out.println("Starting iterative DFS with depth: " + deeplimit);
            startDFS(deeplimit, root);
            deeplimit++;
        }

    }


    public void startDFS(int depth, Node r){
        if (depth == 0){
            //System.out.println("Maximum depth limit");
            return;
        }

        //System.out.println("Processing Level " + r.level + " " + r.data);

        for (String movef : moves){
            State s = r.data.tranSits(movef);

            if (s != null && s.isAllow()) // kiểm tra xem trạng thái này có được cho phép không 
            {

                Node child = new Node(s);
                child.parent = r;
                child.level = r.level + 1;
                child.move = movef + " di chuyển " + child.data.getShore();

                if (!child.isAncestor()){
                    r.chilNodeList.add(child);

                    if (child.data.isSolution()){

                        solutions.add(child);
                        //System.out.println("Found solution " + child.data);
                        return;
                    }
                    else{
                        startDFS(depth - 1, child);
                    }

                }
            }
        }
    }



    public void printSolution(){
        System.out.println("Số lượng solutions:  " + solutions.size());
        ArrayList<Node> stack;
        Iterator<Node> iter = solutions.iterator();
        int i = 1;
        while (iter.hasNext()){

            stack = new ArrayList<Node>();
            Node node = iter.next();
            stack.add(node);
            node = node.parent;
            while (node != null){
                stack.add(node);
                node = node.parent;
            }

            System.out.println("Solution " + i);
            printSequence(stack);
            i++;
        }
    }

    private void printSequence(ArrayList<Node> stack){

        StringBuffer buffer = new StringBuffer();
        buffer.append("Di chuyển: ");
        buffer.append(stack.size() - 1);
        buffer.append("\n");
        for (int i = stack.size() - 1; i >= 0; i--){

            Node n = stack.get(i);
            buffer.append(n.data.toString());
            if (i != 0){

                buffer.append(" --> ");
                buffer.append(stack.get(i - 1).move);
                buffer.append("\n");
            }
        }
        System.out.println(buffer.toString());
    }

    public static void main(String[] args){
        System.out.println("--------------------------");
        crossingRiver obj = new crossingRiver();


        obj.startBreadthFirstSearch();

        System.out.println("--------------------------");
        obj.printSolution();

        // này là Depth first Search nha , nhớ nhìn kĩ coi chừng lộn 
        System.out.println("--------------------------");
        obj.startDepthFirstSearch();

        System.out.println("--------------------------");
        obj.printSolution();

    }

}

