import {useForm} from "react-hook-form";
import {Flocker} from "../api/types.ts";

export const PersonalInformationForm = () => {
    const {register, handleSubmit, watch, formState: {errors}} = useForm();
    const onSubmit = data => {
        console.log(data);
        saveFlocker(data)
    };
    console.log(watch("example"));

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <div>
                First Name <input defaultValue="Al" {...register("firstName", {required: true})} />
            </div>
            <div>
                Last Name <input defaultValue="Coholic" {...register("lastName", {required: true})}/>
            </div>
            <div>
                Date of Birth <input defaultValue="01-01-2000" {...register("dateOfBirth")} />
            </div>
            <div>
                Phone number <input type="number" defaultValue="0612345678" {...register("phoneNumber")} />
            </div>
            {errors.exampleRequired && <span>This field is required</span>}
            <input type={"submit"}/>
        </form>
    )
}

export function saveFlocker(flocker: Flocker) {
    const requestOptions: RequestInit = {
        method: 'PUT',
        headers: {
            accept: "application/json",
            "Content-Type": "application/json",
        },
        body: JSON.stringify(flocker)
    };

    return fetch('/api/flocker', requestOptions)
        .then(response => response.json())
}

