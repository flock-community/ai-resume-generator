export default function Root() {
    return (
        <>
            <div id="sidebar">
                <h1>ResumAide</h1>
                <nav>
                    <ul>
                        <li>
                            <a href={`/personal-information`}>Personal information</a>
                        </li>
                        <li>
                            <a href={`/generate-resume-item`}>Generate resume item</a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div id="detail"></div>
        </>
    );
}