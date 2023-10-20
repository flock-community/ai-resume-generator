import {useForm} from "react-hook-form";

export const PersonalInformationForm = () => {
    const {register, handleSubmit, watch, formState: {errors}} = useForm();
    const onSubmit = data => console.log(data);
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
                Date of Birth <input defaultValue="01-01-2000" {...register("birthDate")} />
            </div>
            {errors.exampleRequired && <span>This field is required</span>}
            <input type={"submit"}/>
        </form>
    )
}

