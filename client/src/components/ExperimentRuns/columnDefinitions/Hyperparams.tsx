import * as React from 'react';

import Draggable from 'components/shared/Draggable/Draggable';
import { ComparisonType, PropertyType } from 'models/Filters';

import styles from './ColumnDefs.module.css';

const ModelProperty: React.SFC<any> = props => {
  return (
    <Draggable
      additionalClassName={styles.param_draggable}
      type="filter"
      data={{
        type: PropertyType.METRIC,
        name: props.property.key,
        value: props.property.value,
        comparisonType: ComparisonType.MORE,
      }}
    >
      <div className={styles.param_grid_hyp}>
        <div className={styles.param_key}>{props.property.key}</div>
        <div className={styles.param_val}>
          {typeof props.property.value === 'number'
            ? Math.round(props.property.value * 10000) / 10000
            : props.property.value}
        </div>
      </div>
    </Draggable>
  );
};

class HyperparamsColDef extends React.Component<any> {
  public render() {
    const hyperparamObject = this.props.value;
    return (
      <div className={styles.param_cell} title={`Drag & Drop To Filter`}>
        {hyperparamObject.map((property: object, i: number) => {
          return <ModelProperty key={i} property={property} />;
        })}
      </div>
    );
  }
}

export default HyperparamsColDef;
