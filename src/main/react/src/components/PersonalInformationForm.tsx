import {useForm} from "react-hook-form";
import {Flocker} from "../api/types.ts";

export const PersonalInformationForm = () => {
    const {register, handleSubmit, formState: {errors}} = useForm<Flocker>();
    const onSubmit = async (flocker: Flocker) => {
        console.log(flocker);
        await saveFlocker(flocker)
    };

    return (
        <form onSubmit={handleSubmit(flocker => onSubmit(flocker))}>
            <div>
                First Name <input defaultValue="Al" {...register("firstName", {required: true})} />
            </div>
            {errors.firstName && <span>This field is required</span>}
            <div>
                Last Name <input defaultValue="Coholic" {...register("lastName", {required: true})}/>
            </div>
            {errors.lastName && <span>This field is required</span>}
            <div>
                Date of Birth <input defaultValue="01-01-2000" {...register("dateOfBirth")} />
            </div>
            <div>
                Phone number <input type="number" defaultValue="0612345678" {...register("phoneNumber")} />
            </div>
            <input type={"submit"}/>
        </form>
    )
}

export function saveFlocker(flocker: Flocker) {

    const requestOptions: RequestInit = {
        method: 'PUT',
        headers: {
            accept: "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(flocker)
    };

    return fetch('/api/flocker', requestOptions)
        .then(response => response.json())
}

