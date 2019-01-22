import * as React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router';

import { IHyperparameter } from 'models/HyperParameters';
import { IModelMetric } from 'models/ModelMetric';
import { Link } from 'react-router-dom';
import { IArtifact } from '../../models/Artifact';
import { Model } from '../../models/Model';
import { fetchModel } from '../../store/model';
import { IApplicationState, IConnectedReduxProps } from '../../store/store';
import ShowContentBasedOnUrl from '../ShowContentBasedOnUrl/ShowContentBasedOnUrl';
import styles from './Model.module.css';

export interface IUrlProps {
  modelId: string;
}

interface IPropsFromState {
  data?: Model | null;
  loading: boolean;
}

type AllProps = RouteComponentProps<IUrlProps> & IPropsFromState & IConnectedReduxProps;

class ModelLayout extends React.Component<AllProps> {
  public render() {
    const { data, loading } = this.props;
    const notNullModel = data || new Model();

    return (
      <div className={styles.model_layout}>
        <div className={styles.model_header}>
          <Link className={styles.path_copy} to={`/project/${notNullModel.ProjectId}/models`}>
            <i className="fa fa-angle-left" />
          </Link>
          <span className={styles.model_name}>Model: {notNullModel.Name}</span>
        </div>
        <div className={styles.model_details}>
          <div>{notNullModel.Id ? `Id: ${notNullModel.Id}` : ''}</div>
          <div>{notNullModel.ProjectId ? `Project: ${notNullModel.ProjectId}` : ''}</div>
          <div>{notNullModel.ExperimentId ? `Experiment: ${notNullModel.ExperimentId}` : ''}</div>
          <div>
            {notNullModel.Tags && notNullModel.Tags.length > 0 ? (
              <div>
                Tags:{' '}
                {notNullModel.Tags.map((value: string, key: number) => {
                  return (
                    <span className={styles.tag} key={key}>
                      {value}
                    </span>
                  );
                })}
              </div>
            ) : (
              ''
            )}
          </div>
          <div>
            {notNullModel.Hyperparameters && notNullModel.Hyperparameters.length > 0 ? (
              <div>
                Hyperparameters: <br />
                <div className={styles.internal_lists}>
                  {notNullModel.Hyperparameters.map((value: IHyperparameter, key: number) => {
                    return (
                      <div key={key}>
                        {value.key}: {value.value}
                      </div>
                    );
                  })}
                </div>
              </div>
            ) : (
              ''
            )}
          </div>
          <div>
            {notNullModel.ModelMetric && notNullModel.ModelMetric.length > 0 ? (
              <div>
                Metrics: <br />
                <div className={styles.internal_lists}>
                  {notNullModel.ModelMetric.map((value: IModelMetric, key: number) => {
                    return (
                      <div key={key}>
                        {value.key}: {value.value}
                      </div>
                    );
                  })}
                </div>
              </div>
            ) : (
              ''
            )}
          </div>

          <div>
            {notNullModel.DataSets && notNullModel.DataSets.length > 0 ? (
              <div>
                DataSets: <br />
                <div className={styles.internal_lists}>
                  {notNullModel.DataSets.map((value: IArtifact, key: number) => {
                    return (
                      <div key={key}>
                        {value.key}: <ShowContentBasedOnUrl path={value.path} />
                      </div>
                    );
                  })}
                </div>
              </div>
            ) : (
              ''
            )}
          </div>
          <div>
            {notNullModel.Artifacts && notNullModel.Artifacts.length > 0 ? (
              <div>
                Artifacts: <br />
                <div className={styles.internal_lists}>
                  {notNullModel.Artifacts.map((value: IArtifact, key: number) => {
                    return (
                      <div key={key}>
                        {value.key}: <ShowContentBasedOnUrl path={value.path} />
                      </div>
                    );
                  })}
                </div>
              </div>
            ) : (
              ''
            )}
          </div>
        </div>
      </div>
    );
  }

  public componentDidMount() {
    this.props.dispatch(fetchModel(this.props.match.params.modelId));
  }
}

const mapStateToProps = ({ model }: IApplicationState) => ({
  data: model.data,
  loading: model.loading
});

export default connect(mapStateToProps)(ModelLayout);