import java.util.*;
class Voter
{
    private String name;

    private int  age;


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age=age;
    }


}





class Candidate {
    private String name;
    private int voteCount;

    public Candidate(String name) {
        this.name = name;
        this.voteCount = 0;
    }

    public String getName()
    {
        return name;
    }

    public int getVoteCount()
    {
        return voteCount;
    }

    public void incrementVoteCount()
    {
        voteCount++;
    }
}

class VotingSystem {
    private List<Candidate> candidates;

    public VotingSystem()
    {
        candidates = new ArrayList<>();
    }

    public void addCandidate(Candidate candidate)
    {
        candidates.add(candidate);
    }

    public List<Candidate> getCandidates()
    {
        return candidates;
    }

    public boolean voteForCandidate(int candidateIndex)
    {
        if (candidateIndex >= 0 && candidateIndex < candidates.size())
        {
            candidates.get(candidateIndex).incrementVoteCount();
            return true;
        }
        return false;
    }
}

public class test {

    public static void main(String[] args)
    {
        System.out.println("**********************************************************************");
        System.out.println("******************WELCOME TO ONLINE VOTING SYSTEM*********************");
        System.out.println("**********************************************************************");

        System.out.println("___________________________________________________________________________________________________________");
        System.out.println();

        System.out.println("**********************************************************************");
        System.out.println("************************ENTER YOUR DETAILS****************************");
        System.out.println("**********************************************************************");


        Scanner scanner = new Scanner(System.in);


        System.out.println();
        System.out.println();


        Voter v1=new Voter();
        System.out.println("Enter Your Name");
        String name=scanner.next();
        scanner.next();
        v1.setName(name);

        System.out.println();
        System.out.println();

        System.out.println("Enter Your age");
        int age=scanner.nextInt();
        v1.setAge(age);

        if(age<18)
        {
            System.out.println("You Are not eligible for the vote");
            System.exit(0);
        }

        else
        {
            System.out.println("You are eigible to vote"+name);
        }





        // Create a voting system
        VotingSystem votingSystem = new VotingSystem();



        // Add candidates
        System.out.println("**********************************************************************");
        System.out.println("**********************************************************************");

        votingSystem.addCandidate(new Candidate("Candidate A"));
        votingSystem.addCandidate(new Candidate("Candidate B"));
        votingSystem.addCandidate(new Candidate("Candidate C"));
        votingSystem.addCandidate(new Candidate("Candidate D"));



        // Display candidates
        List<Candidate> candidates = votingSystem.getCandidates();
        for (int i = 0; i < candidates.size(); i++) {
            System.out.println((i + 1) + ". " + candidates.get(i).getName());
        }

        System.out.println("**********************************************************************");
        System.out.println("**********************************************************************");

        System.out.println();


        // User input: Vote for a candidate
        System.out.print("Enter the candidate number you want to vote for: ");
        int candidateNumber = scanner.nextInt();
        scanner.close();
        System.out.println();


        if (votingSystem.voteForCandidate(candidateNumber - 1)) {
            System.out.println("Thank you for your Valuable vote!");

        } else {
            System.out.println("Invalid candidate number. Please try again.");
        }


        System.out.println();


        // Display final results
        System.out.println("Final Results:");
        for (Candidate candidate : candidates) {
            System.out.println(candidate.getName() + ": " + candidate.getVoteCount() + " votes");
        }
    }
}
