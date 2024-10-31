import { ChangeEvent, HTMLAttributes, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { defaultFormFields, setFormFields } from "../../store/workout/workout.reducer";
import { selectFormFields } from "../../store/workout/workout.selector";
import { Workout } from "../../store/workout/workout.types";
import { OptionContainer, OptionLabel } from "./template-options.styles";

export type TemplateOptionsProps = {
  data: string[];
  type: keyof Workout;
  disabled: boolean;
} & HTMLAttributes<HTMLInputElement>;

const TemplateOptions = ({ data, type, disabled, ...otherProps }: TemplateOptionsProps) => {
  const formFields = useSelector(selectFormFields);
  const [fields, setFields] = useState(defaultFormFields);
  const dispatch = useDispatch();

  useEffect(() => {
    if (formFields) {
      setFields(formFields);
    } else {
      setFields(defaultFormFields);
    }
  }, [formFields]);

  const checkIfExisting = (value: string) => {
    const fieldValue = fields[type];

    if (Array.isArray(fieldValue)) {
      return fieldValue.indexOf(value.toLowerCase()) !== -1;
    } else {
      return fieldValue === value;
    }
  };

  const onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;

    const fieldValue = fields[type];

    let updatedArray;
    if (Array.isArray(fieldValue)) {
      if (fieldValue.indexOf(value) === -1) {
        updatedArray = [...fieldValue, value];
      } else {
        updatedArray = fieldValue.filter((item) => item !== value);
      }
    } else {
      updatedArray = value;
    }

    dispatch(setFormFields({ formFields: { ...fields, [name]: updatedArray } }));
  };

  return (
    <OptionContainer>
      {
        data.map((data, index) => (
          <OptionLabel key={index}>
            <input
              type='checkbox'
              {...otherProps}
              value={data.toLowerCase()}
              name={type}
              onChange={onChangeHandler}
              checked={checkIfExisting(data.toLowerCase())}
              disabled={disabled}
            /> {data}
          </OptionLabel>
        ))
      }
    </OptionContainer>
  );
};

export default TemplateOptions;
