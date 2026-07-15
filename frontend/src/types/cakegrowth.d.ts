// CakeGrowth SDK 类型声明
interface Window {
  CakeGrowth: {
    (command: 'whenReady'): Promise<void>;
    (command: 'pageview', options?: { refreshVisitor: boolean }): void;
    (command: 'destroy', options?: { clearQueue: boolean }): Promise<void>;
    apiKey: string;
    cgv: string;
    utm: string;
    lastPage: any;
    url: string;
    visitorId: string;
    cookieInfo: any;
    linkInfo: any;
    projectInfo: any;
  };
}
