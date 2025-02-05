# Report for Payroll Generator

This report helps you demonstrate your understanding of the concepts. You should write this report after you have completed the project. 

## Technical Questions

1. What does CSV stand for? 
   
comma-separated values. It's a simple text format used to represent tabular data, and mainly uses commas to separate different fields and writes each record on a new line. 

2. Why would you declare `List<IEmployee>` instead of `ArrayList<HourlyEmployee>`?

Using `List<IEmployee>` can store different types of employee items at the same time and no need to create additional collection lists for each employee type. The program depends only on the collection interface List and the employee interface IEmployee, not on the specific ArrayList class or specific employee types. This makes the program more scalable and we can easily replace the collection type for example, to LinkedList. 

3. When you have one class referencing another object, such as storing that object as one of the attributes of the first class - what type of relationship is that called (between has-a and is-a)?

when a class contains objects of another class as its attributes, there is a has-a relationship between the two.
4. Can you provide an example of a has-a relationship in your code (if one exists)?

I think one example is between Employee and its subclasses. HourlyEmployee and SalaryEmployee both extend Employee, meaning HourlyEmployee is a employee and salaryEmployee is a employee. This is shown in the diagram. This inheritance relationship makes sense because both types of employees share core employee characteristics like name, ID, and pay rate but have their own specific way of calculating pay.  
5. Can you provide an example of an is-a relationship in your code (if one exists)?

I think one example can be the Employee class has a EmployeeType. This might be a composition relationship where an EmployeeType is a component of Employee. Each Employee object must have an EmployeeType which is either Hourly or Salary as one of its properties. 
6. What is the difference between an interface and an abstract class?

IEmployee is one of the examole. An interface is basically something that lists methods a class have to implement, but doesn't provide any implementation. In our code, IEmployee says any employee have to be able to get their name, ID, pay rate, etc. but doesn't say how to store or calculate these things. While Employee class is one of the example of an abstract class. It can provide both method declarations and implementations. Our Employee class implements some common methods like getName() and getID(), but leaves calculateGrossPay() as abstract because different types of employees calculate pay differently. 
7. What is the advantage of using an interface over an abstract class?

Multiple interfaces can be implemented by one class. Interfaces provide a cleaner way to define a contract without forcing implementation details. For example, any class that implements IPayStub just needs to provide getPay() and getTaxesPaid(), it doesn't matter how they store or calculate these values. In our program, the interfaces including IEmployee, IPayStub and ITimeCard for defining contracts. 
8. Is the following code valid or not? `List<int> numbers = new ArrayList<int>();`, explain why or why not. If not, explain how you can fix it. 

No, it's not valid because the generic types in Java can only use reference types not primitive types like int, so we can change to `List<Integer> numbers = new ArrayList<Interger>()`.
9. Which class/method is described as the "driver" for your application? 

The driver class is basically the entry point of the program. In our program, the PayrollGenerator class with its main() method should be the driver. We can see from the diagram where PayrollGenerator has dependencies pointing to both Builder and FileUtil. The main() method in PayrollGenerators is where everything starts, and it tells other parts of the program when to do their jobs. 
10. How do you create a temporary folder for JUnit Testing? 

From the TestPayrollGenerator, it shows one example of how to create a temporary folder for JUnit Testing which is using JUnit's built-in @TempDir annotation, and JUnit will create and clean up the directory automatically, and the other way is using JUnit's TemporaryFolder Rule.  
## Deeper Thinking 

Salary Inequality is a major issue in the United States. Even in STEM fields, women are often paid less for [entry level positions](https://www.gsb.stanford.edu/insights/whats-behind-pay-gap-stem-jobs). However, not paying equal salary can hurt representation in the field, and looking from a business perspective, can hurt the company's bottom line has diversity improves innovation and innovation drives profits. 

Having heard these facts, your employer would like data about their salaries to ensure that they are paying their employees fairly. While this is often done 'after pay' by employee surveys and feedback, they have the idea that maybe the payroll system can help them ensure that they are paying their employees fairly. They have given you free reign to explore this idea.

Think through the issue / making sure to cite any resources you use to help you better understand the topic. Then write a paragraph on what changes you would need to make to the system. For example, would there be any additional data points you would need to store in the employee file? Why? Consider what point in the payroll process you may want to look at the data, as different people could have different pretax benefits and highlight that. 

The answer to this is mostly open. We ask that you cite at least two sources to show your understanding of the issue. The TAs will also give feedback on your answer, though will be liberal in grading as long as you show a good faith effort to understand the issue and making an effort to think about how your design to could help meet your employer's goals of salary equity. 

https://www.pewresearch.org/social-trends/2023/03/01/the-enduring-grip-of-the-gender-pay-gap/
https://www.kornferry.com/insights/this-week-in-leadership/why-do-entry-level-men-earn-more-than-women
Let me share an interesting observation from the article about gender pay gaps. One fact is that the gap significantly widens when women are between 35 and 44 years old. This timing isn't random, it coincides with when many women have children under 18 at home. Whether by choice or necessity, parenthood often leads women to pause their careers. But here's the thing that it's not just about this age group. Even at entry-level positions, women are earning less than men. While I don't agree with it, I can understand how this happened historically. For decades, society had pushed stereotypes about gender roles. There's still this widespread assumption that if someone needs to stay home with the kids, it's probably going to be mom not dad. This mindset had likely influenced how companies think about compensation. What's really concerning is that the gender salary gap isn't shrinking much. Even though some companies are trying to improve diversity and equality, there's an interesting catch that when companies offer higher compensation and retention bonuses to keep employees from leaving, it can actaully make the pay gap worse. 
To help address this through our payroll system, I think we could make some important changes. We could create a class that analyzes basic pay rates by looking at factors like job title, years of experience, and education level. The key is keeping all this information private and secure. This would let us compare salaries between people of different genders who have the same job title and experience. Of course, we would also need to consider other factors that legitimately affect pay, like job performance. The goal isn't to oversimplify the issue, but to help identify where unexplained gaps might exist. By tracking these patterns over time, companies could spot potential inequities early and take steps to address them, rather than letting gaps widen as careers progress. 
