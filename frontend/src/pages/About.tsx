export const About = () => {
    return (
        <>
            <p>About resource:</p>

            <p>This website represents a repository of quotations said by famous people.
                Users can browse random quotes, or random quotes in specified language(​​Russian or English).
                Amount of quotations on page specified as URL parameter and can be adjusted by user.
                Also, users can browse specific quotes referred by ID's. To post a new quote user must register
                and be authenticated.
            </p>
            <p>There are two ways to publish a new quote: user can fill a form in his/her profile page,
                or upload an XML file. Template available in user profile and contains XML schema which ensures
                file validity.
                Quotations and user accounts can be stored in XML files, or in database.
            </p>
            <p>In addition, to allow users access quotes from scripts, there is an API which provides access to
                single,
                randomly picked quote in Russian or English, or random quote in specified language.
            </p>


            <h4>Currently used technologies:</h4>
            <ul>
                <li>Spring 4</li>
                <li>Spring Boot</li>
                <li>Spring Security</li>
                <li>Spring JDBC templates, JAXB, Hibernate</li>
                <li>Thymeleaf</li>
                <li>JUnit, Mockito, AssertJ</li>
            </ul>
        </>
    );
}