import Project from 'models/Project';

export enum fetchProjectsActionTypes {
  FETCH_PROJECTS_REQUEST = '@@projects/FETCH_PROJECTS_REQUEST',
  FETCH_PROJECTS_SUCESS = '@@projects/FETCH_PROJECTS_SUCESS',
  FETCH_PROJECTS_FAILURE = '@@projects/FETCH_PROJECTS_FAILURE'
}

export type fetchProjectsAction =
  | { type: fetchProjectsActionTypes.FETCH_PROJECTS_REQUEST }
  | { type: fetchProjectsActionTypes.FETCH_PROJECTS_SUCESS; payload: Project[] }
  | { type: fetchProjectsActionTypes.FETCH_PROJECTS_FAILURE };

export interface IProjectsState {
  readonly loading: boolean;
  readonly data?: Project[] | null;
}

export interface IProjectState {
  readonly loading: boolean;
  readonly data?: Project | null;
}

export enum projectFetchModelsActionTypes {
  FETCH_MODELS_REQUEST = '@@project/FETCH_MODELS_REQUEST',
  FETCH_MODELS_SUCESS = '@@project/FETCH_MODELS_SUCESS',
  FETCH_MODELS_FAILURE = '@@project/FETCH_MODELS_FAILURE'
}

export type projectFetchModelsAction =
  | { type: projectFetchModelsActionTypes.FETCH_MODELS_REQUEST }
  | { type: projectFetchModelsActionTypes.FETCH_MODELS_SUCESS; payload: Project }
  | { type: projectFetchModelsActionTypes.FETCH_MODELS_FAILURE };